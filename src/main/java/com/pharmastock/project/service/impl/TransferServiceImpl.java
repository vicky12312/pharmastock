package com.pharmastock.project.service.impl;

import com.pharmastock.project.dto.TransferItemDTO;
import com.pharmastock.project.dto.TransferOrderDTO;
import com.pharmastock.project.entity.*;
import com.pharmastock.project.entity.enums.TransferStatus;
import com.pharmastock.project.entity.enums.TxnType;
import com.pharmastock.project.exception.ResourceNotFoundException;
import com.pharmastock.project.exception.ValidationException;
import com.pharmastock.project.repository.*;
import com.pharmastock.project.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final TransferOrderRepository toRepository;
    private final TransferItemRepository toItemRepository;
    private final ItemRepository itemRepository;
    private final InventoryBalanceRepository balanceRepository;
    private final StockTransactionRepository txnRepository;

    @Override
    public TransferOrderDTO createTransfer(TransferOrderDTO dto) {
        if (dto.getFromLocationId().equals(dto.getToLocationId())) {
            throw new ValidationException("Source and destination must be different");
        }

        TransferOrder to = TransferOrder.builder()
                .fromLocationId(dto.getFromLocationId())
                .toLocationId(dto.getToLocationId())
                .createdDate(LocalDateTime.now())
                .status(TransferStatus.OPEN)
                .build();

        List<TransferItem> mappedItems = new ArrayList<>();
        if (dto.getItems() != null) {
            for (TransferItemDTO i : dto.getItems()) {
                Item item = itemRepository.findById(i.getItemId())
                        .orElseThrow(() -> new ResourceNotFoundException("Item not found"));
                TransferItem ti = TransferItem.builder()
                        .transferOrder(to)
                        .item(item)
                        .quantity(i.getQuantity())
                        .build();
                mappedItems.add(ti);
            }
        }
        to.setItems(mappedItems);
        TransferOrder saved = toRepository.save(to);
        return mapToDTO(saved);
    }

    @Override
    public TransferOrderDTO getTransfer(Long id) {
        return mapToDTO(toRepository.findById(id).orElseThrow());
    }

    @Override
    @Transactional
    public void pickTransfer(Long toId) {
        TransferOrder to = toRepository.findById(toId).orElseThrow();
        if (to.getStatus() != TransferStatus.OPEN) throw new ValidationException("Only OPEN transfers can be picked");

        for (TransferItem ti : to.getItems()) {
            InventoryBalance sourceBalance = balanceRepository.findByItemItemId(ti.getItem().getItemId()).stream()
                    .filter(b -> b.getLocationId().equals(to.getFromLocationId()))
                    .filter(b -> (b.getQuantityOnHand() - b.getReservedQty()) >= ti.getQuantity())
                    .findFirst().orElseThrow(() -> new ValidationException("Insufficient stock for item: " + ti.getItem().getItemId()));

            ti.setLot(sourceBalance.getLot());
            sourceBalance.setQuantityOnHand(sourceBalance.getQuantityOnHand() - ti.getQuantity());
            balanceRepository.save(sourceBalance);

            StockTransaction txn = StockTransaction.builder()
                    .locationId(to.getFromLocationId())
                    .bin(sourceBalance.getBin())
                    .item(ti.getItem())
                    .lot(sourceBalance.getLot())
                    .txnType(TxnType.TRANSFER_OUT)
                    .quantity(ti.getQuantity())
                    .txnDate(LocalDateTime.now())
                    .referenceId("TO-" + to.getToId())
                    .build();
            txnRepository.save(txn);
        }
        to.setStatus(TransferStatus.PICKED);
        toRepository.save(to);
    }

    @Override
    public void shipTransfer(Long toId) {
        TransferOrder to = toRepository.findById(toId).orElseThrow();
        if (to.getStatus() != TransferStatus.PICKED) throw new ValidationException("Only PICKED transfers can be shipped");
        to.setStatus(TransferStatus.SHIPPED);
        toRepository.save(to);
    }

    @Override
    @Transactional
    public void receiveTransfer(Long toId) {
        TransferOrder to = toRepository.findById(toId).orElseThrow();
        if (to.getStatus() != TransferStatus.SHIPPED) throw new ValidationException("Only SHIPPED transfers can be received");

        for (TransferItem ti : to.getItems()) {
            InventoryBalance destBalance = balanceRepository.findByItemItemId(ti.getItem().getItemId()).stream()
                    .filter(b -> b.getLocationId().equals(to.getToLocationId()))
                    .filter(b -> b.getLot().getLotId().equals(ti.getLot().getLotId()))
                    .findFirst().orElseGet(() -> {
                        Bin defaultBin = balanceRepository.findAll().get(0).getBin(); // Just logic placeholder
                        return InventoryBalance.builder()
                            .locationId(to.getToLocationId())
                            .bin(defaultBin)
                            .item(ti.getItem())
                            .lot(ti.getLot())
                            .quantityOnHand(0)
                            .reservedQty(0)
                            .build();
                    });
            
            destBalance.setQuantityOnHand(destBalance.getQuantityOnHand() + ti.getQuantity());
            balanceRepository.save(destBalance);

            StockTransaction txn = StockTransaction.builder()
                    .locationId(to.getToLocationId())
                    .bin(destBalance.getBin())
                    .item(ti.getItem())
                    .lot(ti.getLot())
                    .txnType(TxnType.TRANSFER_IN)
                    .quantity(ti.getQuantity())
                    .txnDate(LocalDateTime.now())
                    .referenceId("TO-" + to.getToId())
                    .build();
            txnRepository.save(txn);
        }
        to.setStatus(TransferStatus.RECEIVED);
        toRepository.save(to);
    }

    private TransferOrderDTO mapToDTO(TransferOrder to) {
        List<TransferItemDTO> itemDTOs = to.getItems().stream().map(ti -> TransferItemDTO.builder()
                .toItemId(ti.getToItemId())
                .toId(ti.getTransferOrder().getToId())
                .itemId(ti.getItem().getItemId())
                .lotId(ti.getLot() != null ? ti.getLot().getLotId() : null)
                .quantity(ti.getQuantity())
                .build()).collect(Collectors.toList());

        return TransferOrderDTO.builder()
                .toId(to.getToId())
                .fromLocationId(to.getFromLocationId())
                .toLocationId(to.getToLocationId())
                .createdDate(to.getCreatedDate())
                .status(to.getStatus())
                .items(itemDTOs)
                .build();
    }
}
