package com.pharmastock.project.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.pharmastock.project.dto.InventoryReportDTO;
import com.pharmastock.project.entity.InventoryReport;
import com.pharmastock.project.repository.InventoryReportRepository;
import com.pharmastock.project.service.ReportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final InventoryReportRepository reportRepository;

    @Override
    public InventoryReportDTO getNearExpiryReport() {
        return generateDummyReport("EXPIRY", "{\"items_near_expiry\": 15, \"critical\": 3}");
    }

    @Override
    public InventoryReportDTO getStockValuation() {
        return generateDummyReport("VALUATION", "{\"total_value\": 145000.50, \"currency\": \"USD\"}");
    }

    @Override
    public InventoryReportDTO getNonMovingItems() {
        return generateDummyReport("NON_MOVING", "{\"items_no_movement_90_days\": 42}");
    }

    @Override
    public InventoryReportDTO getConsumptionReport() {
        return generateDummyReport("CONSUMPTION", "{\"top_consumed_item\": \"Paracetamol 500mg\", \"qty\": 5000}");
    }

    private InventoryReportDTO generateDummyReport(String scope, String metrics) {
        InventoryReport report = InventoryReport.builder()
                .scope(scope)
                .metrics(metrics)
                .generatedDate(LocalDateTime.now())
                .build();
        InventoryReport saved = reportRepository.save(report);

        return InventoryReportDTO.builder()
                .reportId(saved.getReportId())
                .scope(saved.getScope())
                .metrics(saved.getMetrics())
                .generatedDate(saved.getGeneratedDate())
                .build();
    }
}
