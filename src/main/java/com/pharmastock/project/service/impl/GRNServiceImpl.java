package com.pharmastock.project.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pharmastock.project.dto.GRNItemDTO;
import com.pharmastock.project.dto.GoodsReceiptDTO;
import com.pharmastock.project.entity.Bin;
import com.pharmastock.project.entity.GRNItem;
import com.pharmastock.project.entity.GoodsReceipt;
import com.pharmastock.project.entity.InventoryBalance;
import com.pharmastock.project.entity.InventoryLot;
import com.pharmastock.project.entity.Item;
import com.pharmastock.project.entity.POItem;
import com.pharmastock.project.entity.PurchaseOrder;
import com.pharmastock.project.entity.PutAwayTask;
import com.pharmastock.project.entity.StockTransaction;
import com.pharmastock.project.entity.enums.GRNStatus;
import com.pharmastock.project.entity.enums.LotStatus;
import com.pharmastock.project.entity.enums.POStatus;
import com.pharmastock.project.entity.enums.TaskStatus;
import com.pharmastock.project.entity.enums.TxnType;
import com.pharmastock.project.exception.ResourceNotFoundException;
import com.pharmastock.project.exception.ValidationException;
import com.pharmastock.project.repository.BinRepository;
import com.pharmastock.project.repository.GRNItemRepository;
import com.pharmastock.project.repository.GoodsReceiptRepository;
import com.pharmastock.project.repository.InventoryBalanceRepository;
import com.pharmastock.project.repository.InventoryLotRepository;
import com.pharmastock.project.repository.ItemRepository;
import com.pharmastock.project.repository.POItemRepository;
import com.pharmastock.project.repository.PurchaseOrderRepository;
import com.pharmastock.project.repository.PutAwayTaskRepository;
import com.pharmastock.project.repository.StockTransactionRepository;
import com.pharmastock.project.service.GRNService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GRNServiceImpl implements GRNService {

    private final GoodsReceiptRepository grnRepository;
    private final PurchaseOrderRepository poRepository;
    private final GRNItemRepository grnItemRepository;
    private final POItemRepository poItemRepository;
    private final ItemRepository itemRepository;
    private final InventoryLotRepository lotRepository;
    private final InventoryBalanceRepository balanceRepository;
    private final StockTransactionRepository stockTransactionRepository;
    private final PutAwayTaskRepository putAwayTaskRepository;
    private final BinRepository binRepository;

    @Override
    public GoodsReceiptDTO createGRN(GoodsReceiptDTO dto) {
        PurchaseOrder po = poRepository.findById(dto.getPoId())
                .orElseThrow(() -> new ResourceNotFoundException("PO not found"));

        if (po.getStatus() != POStatus.APPROVED && po.getStatus() != POStatus.PARTIALLY_RECEIVED) {
            throw new ValidationException("GRN can only be created for APPROVED or PARTIALLY_RECEIVED POs");
        }

        GoodsReceipt grn = GoodsReceipt.builder()
                .purchaseOrder(po)
                .receivedBy(dto.getReceivedBy())
                .receivedDate(dto.getReceivedDate() != null ? dto.getReceivedDate() : LocalDateTime.now())
                .status(GRNStatus.OPEN)
                .build();

        GoodsReceipt savedGrn = grnRepository.save(grn);

        if (dto.getItems() != null) {
            for (GRNItemDTO itemDto : dto.getItems()) {
                if (itemDto.getReceivedQty() != (itemDto.getAcceptedQty() + itemDto.getRejectedQty())) {
                    throw new ValidationException("Received quantity must equal accepted + rejected quantities");
                }
                if (itemDto.getExpiryDate().isBefore(LocalDate.now())) {
                    throw new ValidationException("Cannot receive expired items");
                }

                POItem poItem = poItemRepository.findById(itemDto.getPoItemId())
                        .orElseThrow(() -> new ResourceNotFoundException("PO Item not found"));
                Item item = itemRepository.findById(itemDto.getItemId())
                        .orElseThrow(() -> new ResourceNotFoundException("Item not found"));

                GRNItem grnItem = GRNItem.builder()
                        .grn(savedGrn)
                        .poItem(poItem)
                        .item(item)
                        .batchNumber(itemDto.getBatchNumber())
                        .expiryDate(itemDto.getExpiryDate())
                        .receivedQty(itemDto.getReceivedQty())
                        .acceptedQty(itemDto.getAcceptedQty())
                        .rejectedQty(itemDto.getRejectedQty())
                        .reason(itemDto.getReason())
                        .build();
                grnItemRepository.save(grnItem);
            }
        }

        return getGRNById(savedGrn.getGrnId());
    }

    @Override
    public GoodsReceiptDTO getGRNById(Long id) {
        GoodsReceipt grn = grnRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GRN not found"));

        List<GRNItemDTO> items = grnItemRepository.findByGrnGrnId(id).stream()
                .map(i -> GRNItemDTO.builder()
                        .grnItemId(i.getGrnItemId())
                        .poItemId(i.getPoItem().getPoItemId())
                        .itemId(i.getItem().getItemId())
                        .batchNumber(i.getBatchNumber())
                        .expiryDate(i.getExpiryDate())
                        .receivedQty(i.getReceivedQty())
                        .acceptedQty(i.getAcceptedQty())
                        .rejectedQty(i.getRejectedQty())
                        .reason(i.getReason())
                        .build())
                .collect(Collectors.toList());

        return GoodsReceiptDTO.builder()
                .grnId(grn.getGrnId())
                .poId(grn.getPurchaseOrder().getPoId())
                .receivedBy(grn.getReceivedBy())
                .receivedDate(grn.getReceivedDate())
                .status(grn.getStatus())
                .items(items)
                .build();
    }

    @Override
    @Transactional
    public void postGRN(Long id) {
        GoodsReceipt grn = grnRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GRN not found"));

        if (grn.getStatus() != GRNStatus.OPEN) {
            throw new ValidationException("Only OPEN GRNs can be posted");
        }

        List<GRNItem> items = grnItemRepository.findByGrnGrnId(id);
        if (items.isEmpty()) {
            throw new ValidationException("Cannot post GRN with no items");
        }

        Long locationId = grn.getPurchaseOrder().getLocation().getLocationId();

        // Find default receiving bin or just the first bin for the location for simplicity in this logic
        List<Bin> locationBins = binRepository.findAll().stream()
                .filter(b -> b.getLocation().getLocationId().equals(locationId))
                .collect(Collectors.toList());
        if (locationBins.isEmpty()) {
            throw new ValidationException("No bins found in PO location for storing stock");
        }
        Bin defaultBin = locationBins.get(0); // For demo, grab the first valid bin

        for (GRNItem item : items) {
            if (item.getAcceptedQty() > 0) {
                // 1. Inventory Lot
                InventoryLot lot = lotRepository.findByItemItemIdAndBatchNumberAndExpiryDate(
                        item.getItem().getItemId(), item.getBatchNumber(), item.getExpiryDate())
                        .orElseGet(() -> {
                            InventoryLot newLot = InventoryLot.builder()
                                    .item(item.getItem())
                                    .batchNumber(item.getBatchNumber())
                                    .expiryDate(item.getExpiryDate())
                                    .status(LotStatus.AVAILABLE)
                                    .build();
                            return lotRepository.save(newLot);
                        });

                // 2. Inventory Balance
                InventoryBalance balance = balanceRepository.findByLocationIdAndBinBinIdAndItemItemIdAndLotLotId(
                        locationId, defaultBin.getBinId(), item.getItem().getItemId(), lot.getLotId())
                        .orElseGet(() -> {
                            return InventoryBalance.builder()
                                    .locationId(locationId)
                                    .bin(defaultBin)
                                    .item(item.getItem())
                                    .lot(lot)
                                    .quantityOnHand(0)
                                    .reservedQty(0)
                                    .build();
                        });

                balance.setQuantityOnHand(balance.getQuantityOnHand() + item.getAcceptedQty());
                balanceRepository.save(balance);

                // 3. Stock Transaction
                StockTransaction txn = StockTransaction.builder()
                        .locationId(locationId)
                        .bin(defaultBin)
                        .item(item.getItem())
                        .lot(lot)
                        .txnType(TxnType.RECEIPT)
                        .quantity(item.getAcceptedQty())
                        .txnDate(LocalDateTime.now())
                        .referenceId("GRN-" + grn.getGrnId())
                        .build();
                stockTransactionRepository.save(txn);

                // 4. Put-Away Task
                PutAwayTask putAwayTask = PutAwayTask.builder()
                        .grnItem(item)
                        .targetBin(defaultBin)
                        .quantity(item.getAcceptedQty())
                        .status(TaskStatus.PENDING)
                        .build();
                putAwayTaskRepository.save(putAwayTask);
            }
        }

        grn.setStatus(GRNStatus.POSTED);
        grnRepository.save(grn);
    }
}
