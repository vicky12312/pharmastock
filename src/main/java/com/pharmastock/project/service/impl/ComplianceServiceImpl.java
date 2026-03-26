package com.pharmastock.project.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pharmastock.project.dto.ColdChainLogDTO;
import com.pharmastock.project.dto.QuarantineActionDTO;
import com.pharmastock.project.dto.RecallNoticeDTO;
import com.pharmastock.project.entity.ColdChainLog;
import com.pharmastock.project.entity.Drug;
import com.pharmastock.project.entity.InventoryLot;
import com.pharmastock.project.entity.Item;
import com.pharmastock.project.entity.QuarantineAction;
import com.pharmastock.project.entity.RecallNotice;
import com.pharmastock.project.entity.enums.ColdChainStatus;
import com.pharmastock.project.entity.enums.LotStatus;
import com.pharmastock.project.entity.enums.QAStatus;
import com.pharmastock.project.entity.enums.RecallStatus;
import com.pharmastock.project.repository.ColdChainLogRepository;
import com.pharmastock.project.repository.DrugRepository;
import com.pharmastock.project.repository.InventoryLotRepository;
import com.pharmastock.project.repository.ItemRepository;
import com.pharmastock.project.repository.QuarantineActionRepository;
import com.pharmastock.project.repository.RecallNoticeRepository;
import com.pharmastock.project.service.ComplianceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComplianceServiceImpl implements ComplianceService {

    private final ColdChainLogRepository coldChainLogRepository;
    private final RecallNoticeRepository recallNoticeRepository;
    private final QuarantineActionRepository quarantineActionRepository;
    private final InventoryLotRepository lotRepository;
    private final DrugRepository drugRepository;
    private final ItemRepository itemRepository;

    @Override
    public ColdChainLogDTO logColdChain(ColdChainLogDTO dto) {
        ColdChainLog log = ColdChainLog.builder()
                .locationId(dto.getLocationId())
                .sensorId(dto.getSensorId())
                .timestamp(dto.getTimestamp() != null ? dto.getTimestamp() : LocalDateTime.now())
                .temperatureC(dto.getTemperatureC())
                .status(dto.getStatus() != null ? dto.getStatus() : ColdChainStatus.NORMAL)
                .build();
        ColdChainLog saved = coldChainLogRepository.save(log);

        return ColdChainLogDTO.builder()
                .logId(saved.getLogId())
                .locationId(saved.getLocationId())
                .sensorId(saved.getSensorId())
                .timestamp(saved.getTimestamp())
                .temperatureC(saved.getTemperatureC())
                .status(saved.getStatus())
                .build();
    }

    @Override
    @Transactional
    public RecallNoticeDTO createRecall(RecallNoticeDTO dto) {
        Drug drug = drugRepository.findById(dto.getDrugId()).orElseThrow();
        RecallNotice recall = RecallNotice.builder()
                .drug(drug)
                .noticeDate(LocalDateTime.now())
                .reason(dto.getReason())
                .action(dto.getAction())
                .status(RecallStatus.OPEN)
                .build();
        RecallNotice saved = recallNoticeRepository.save(recall);

        // Find all items linked to this drug
        List<Item> items = itemRepository.findAll().stream()
                .filter(i -> i.getDrug().getDrugId().equals(drug.getDrugId()))
                .toList();

        for (Item item : items) {
            List<InventoryLot> lots = lotRepository.findByItemItemIdOrderByExpiryDateAsc(item.getItemId());
            for (InventoryLot lot : lots) {
                if (lot.getStatus() != LotStatus.QUARANTINE) { // Lock it
                    lot.setStatus(LotStatus.QUARANTINE);
                    lotRepository.save(lot);

                    QuarantineAction qa = QuarantineAction.builder()
                            .lot(lot)
                            .quarantineDate(LocalDateTime.now())
                            .reason("RECALL: " + saved.getReason())
                            .status(QAStatus.QUARANTINED)
                            .build();
                    quarantineActionRepository.save(qa);
                }
            }
        }

        return RecallNoticeDTO.builder()
                .recallId(saved.getRecallId())
                .drugId(saved.getDrug().getDrugId())
                .noticeDate(saved.getNoticeDate())
                .reason(saved.getReason())
                .action(saved.getAction())
                .status(saved.getStatus())
                .build();
    }

    @Override
    @Transactional
    public QuarantineActionDTO quarantineLot(Long lotId, String reason) {
        InventoryLot lot = lotRepository.findById(lotId).orElseThrow();
        lot.setStatus(LotStatus.QUARANTINE);
        lotRepository.save(lot);

        QuarantineAction qa = QuarantineAction.builder()
                .lot(lot)
                .quarantineDate(LocalDateTime.now())
                .reason(reason)
                .status(QAStatus.QUARANTINED)
                .build();
        QuarantineAction saved = quarantineActionRepository.save(qa);

        return QuarantineActionDTO.builder()
                .qaId(saved.getQaId())
                .lotId(saved.getLot().getLotId())
                .quarantineDate(saved.getQuarantineDate())
                .reason(saved.getReason())
                .status(saved.getStatus())
                .build();
    }

    @Override
    @Transactional
    public void releaseQuarantine(Long qaId) {
        QuarantineAction qa = quarantineActionRepository.findById(qaId).orElseThrow();
        qa.setStatus(QAStatus.RELEASED);
        qa.setReleasedDate(LocalDateTime.now());
        quarantineActionRepository.save(qa);

        InventoryLot lot = qa.getLot();
        lot.setStatus(LotStatus.AVAILABLE);
        lotRepository.save(lot);
    }
}
