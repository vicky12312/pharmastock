package com.pharmastock.project.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pharmastock.project.dto.DispenseRefDTO;
import com.pharmastock.project.entity.DispenseRef;
import com.pharmastock.project.entity.InventoryBalance;
import com.pharmastock.project.entity.Item;
import com.pharmastock.project.entity.StockTransaction;
import com.pharmastock.project.entity.enums.DispenseStatus;
import com.pharmastock.project.entity.enums.TxnType;
import com.pharmastock.project.exception.ResourceNotFoundException;
import com.pharmastock.project.exception.ValidationException;
import com.pharmastock.project.repository.DispenseRefRepository;
import com.pharmastock.project.repository.InventoryBalanceRepository;
import com.pharmastock.project.repository.ItemRepository;
import com.pharmastock.project.repository.StockTransactionRepository;
import com.pharmastock.project.service.DispenseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DispenseServiceImpl implements DispenseService {

    private final DispenseRefRepository dispenseRefRepository;
    private final InventoryBalanceRepository balanceRepository;
    private final ItemRepository itemRepository;
    private final StockTransactionRepository stockTransactionRepository;

    @Override
    @Transactional
    public DispenseRefDTO dispenseItem(DispenseRefDTO dto) {
        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));

        List<InventoryBalance> balances = balanceRepository.findByItemItemId(dto.getItemId());
        // Find a specific location balance
        InventoryBalance sourceBalance = balances.stream()
                .filter(b -> b.getLocationId().equals(dto.getLocationId()))
                .filter(b -> (b.getQuantityOnHand() - b.getReservedQty()) >= dto.getQuantity())
                .findFirst()
                .orElseThrow(() -> new ValidationException("Insufficient unreserved stock available in location"));

        // Deduct inventory
        sourceBalance.setQuantityOnHand(sourceBalance.getQuantityOnHand() - dto.getQuantity());
        balanceRepository.save(sourceBalance);

        DispenseRef dispenseRef = DispenseRef.builder()
                .locationId(dto.getLocationId())
                .item(item)
                .lot(sourceBalance.getLot()) // Grab the specifically mapped FEFO lot from the balance
                .quantity(dto.getQuantity())
                .dispenseDate(dto.getDispenseDate() != null ? dto.getDispenseDate() : LocalDateTime.now())
                .destination(dto.getDestination())
                .status(DispenseStatus.POSTED)
                .build();
        DispenseRef saved = dispenseRefRepository.save(dispenseRef);

        StockTransaction txn = StockTransaction.builder()
                .locationId(dto.getLocationId())
                .bin(sourceBalance.getBin())
                .item(item)
                .lot(sourceBalance.getLot())
                .txnType(TxnType.ISSUE)
                .quantity(dto.getQuantity())
                .txnDate(LocalDateTime.now())
                .referenceId("DISP-" + saved.getDispenseId())
                .build();
        stockTransactionRepository.save(txn);

        return DispenseRefDTO.builder()
                .dispenseId(saved.getDispenseId())
                .locationId(saved.getLocationId())
                .itemId(saved.getItem().getItemId())
                .lotId(saved.getLot().getLotId())
                .quantity(saved.getQuantity())
                .dispenseDate(saved.getDispenseDate())
                .destination(saved.getDestination())
                .status(saved.getStatus())
                .build();
    }
}
