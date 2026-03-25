package com.pharmastock.project.service;
import com.pharmastock.project.dto.InventoryReportDTO;
public interface ReportService {
    InventoryReportDTO getNearExpiryReport();
    InventoryReportDTO getStockValuation();
    InventoryReportDTO getNonMovingItems();
    InventoryReportDTO getConsumptionReport();
}
