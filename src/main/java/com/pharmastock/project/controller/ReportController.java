package com.pharmastock.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmastock.project.dto.InventoryReportDTO;
import com.pharmastock.project.service.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/near-expiry")
    public ResponseEntity<InventoryReportDTO> getNearExpiryReport() {
        return ResponseEntity.ok(reportService.getNearExpiryReport());
    }

    @GetMapping("/stock-value")
    public ResponseEntity<InventoryReportDTO> getStockValuation() {
        return ResponseEntity.ok(reportService.getStockValuation());
    }

    @GetMapping("/non-moving")
    public ResponseEntity<InventoryReportDTO> getNonMovingItems() {
        return ResponseEntity.ok(reportService.getNonMovingItems());
    }

    @GetMapping("/consumption")
    public ResponseEntity<InventoryReportDTO> getConsumptionReport() {
        return ResponseEntity.ok(reportService.getConsumptionReport());
    }
}
