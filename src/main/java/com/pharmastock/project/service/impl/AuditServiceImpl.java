package com.pharmastock.project.service.impl;

import com.pharmastock.project.dto.StockCountDTO;
import com.pharmastock.project.dto.StockCountItemDTO;
import com.pharmastock.project.entity.*;
import com.pharmastock.project.entity.enums.CountStatus;
import com.pharmastock.project.entity.enums.TxnType;
import com.pharmastock.project.exception.ResourceNotFoundException;
import com.pharmastock.project.repository.*;
import com.pharmastock.project.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final StockCountRepository countRepository ;
    private final StockCountItemRepository countItemRepository;
    private final StockAdjustmentRepository adjustmentRepository;
    private final InventoryBalanceRepository balanceRepository;
    private final StockTransactionRepository transactionRepository;

    @Override
    public StockCountDTO createCount(StockCountDTO dto) {
        StockCount count = StockCount.builder()
                .locationId(dto.getLocationId())
                .cycle(dto.getCycle())
                .scheduledDate(dto.getScheduledDate() != null ? dto.getScheduledDate() : LocalDateTime.now())
                .status(CountStatus.PLANNED)
                .build();
        StockCount saved = countRepository.save(count);
        return mapToDTO(saved);
    }

    @Override
    public void startCount(Long countId) {
        StockCount count = countRepository.findById(countId).orElseThrow();
        count.setStatus(CountStatus.IN_PROGRESS);
        countRepository.save(count);
    }

    @Override
    @Transactional
    public void postCount(Long countId) {
        StockCount count = countRepository.findById(countId).orElseThrow();
        count.setStatus(CountStatus.POSTED);
        countRepository.save(count);

        for (StockCountItem item : countItemRepository.findByStockCountCountId(countId)) {
            if (item.getVariance() != 0) {
                StockAdjustment adj = StockAdjustment.builder()
                        .locationId(count.getLocationId())
                        .item(item.getItem())
                        .lot(item.getLot())
                        .quantityDelta(item.getVariance())
                        .reason(item.getReasonCode())
                        .build();
                adjustmentRepository.save(adj);
            }
        }
    }

    @Override
    @Transactional
    public void approveAdjustment(Long adjustmentId, String approver) {
        StockAdjustment adj = adjustmentRepository.findById(adjustmentId).orElseThrow();
        adj.setApprovedBy(approver);
        adj.setPostedDate(LocalDateTime.now());
        adjustmentRepository.save(adj);

        InventoryBalance balance = balanceRepository.findByItemItemId(adj.getItem().getItemId()).stream()
                .filter(b -> b.getLocationId().equals(adj.getLocationId()))
                .filter(b -> b.getLot().getLotId().equals(adj.getLot().getLotId()))
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Balance not found for adjustment"));

        balance.setQuantityOnHand(balance.getQuantityOnHand() + adj.getQuantityDelta());
        balanceRepository.save(balance);

        StockTransaction txn = StockTransaction.builder()
                .locationId(adj.getLocationId())
                .bin(balance.getBin())
                .item(adj.getItem())
                .lot(adj.getLot())
                .txnType(TxnType.ADJUST)
                .quantity(Math.abs(adj.getQuantityDelta()))
                .txnDate(LocalDateTime.now())
                .referenceId("ADJ-" + adj.getAdjustmentId())
                .notes("Delta: " + adj.getQuantityDelta())
                .build();
        transactionRepository.save(txn);
    }

    private StockCountDTO mapToDTO(StockCount c) {
        return StockCountDTO.builder()
                .countId(c.getCountId())
                .locationId(c.getLocationId())
                .cycle(c.getCycle())
                .scheduledDate(c.getScheduledDate())
                .status(c.getStatus())
                .build();
    }
}
