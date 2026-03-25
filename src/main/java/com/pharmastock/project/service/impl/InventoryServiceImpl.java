package com.pharmastock.project.service.impl;

import com.pharmastock.project.dto.InventoryLotDTO;
import com.pharmastock.project.entity.ExpiryWatch;
import com.pharmastock.project.entity.InventoryLot;
import com.pharmastock.project.entity.enums.LotStatus;
import com.pharmastock.project.entity.enums.WatchStatus;
import com.pharmastock.project.repository.ExpiryWatchRepository;
import com.pharmastock.project.repository.InventoryLotRepository;
import com.pharmastock.project.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryLotRepository lotRepository;
    private final ExpiryWatchRepository watchRepository;

    @Override
    public List<InventoryLotDTO> getLotsForFefo(Long itemId) {
        return lotRepository.findByItemItemIdOrderByExpiryDateAsc(itemId).stream()
                .filter(lot -> lot.getStatus() == LotStatus.AVAILABLE)
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void checkExpiryWatches() {
        LocalDate today = LocalDate.now();
        List<InventoryLot> allLots = lotRepository.findAll();
        
        for (InventoryLot lot : allLots) {
            if (lot.getStatus() == LotStatus.AVAILABLE) {
                long days = ChronoUnit.DAYS.between(today, lot.getExpiryDate());
                if (days <= 30) {
                    ExpiryWatch watch = ExpiryWatch.builder()
                            .lot(lot)
                            .daysToExpire((int) days)
                            .flagDate(today)
                            .status(WatchStatus.OPEN)
                            .build();
                    watchRepository.save(watch);
                    
                    // Auto quarantine if expired
                    if (days < 0) {
                        lot.setStatus(LotStatus.QUARANTINE);
                        lotRepository.save(lot);
                    }
                }
            }
        }
    }

    private InventoryLotDTO mapToDTO(InventoryLot lot) {
        return InventoryLotDTO.builder()
                .lotId(lot.getLotId())
                .itemId(lot.getItem().getItemId())
                .batchNumber(lot.getBatchNumber())
                .expiryDate(lot.getExpiryDate())
                .manufacturer(lot.getManufacturer())
                .status(lot.getStatus())
                .build();
    }
}
