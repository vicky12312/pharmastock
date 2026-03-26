package com.pharmastock.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pharmastock.project.dto.PurchaseOrderDTO;
import com.pharmastock.project.entity.enums.POStatus;
import com.pharmastock.project.service.PurchaseOrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/procurement/po")
@RequiredArgsConstructor
@Slf4j
public class PurchaseOrderController {

    private final PurchaseOrderService poService;

    @PostMapping
    public ResponseEntity<PurchaseOrderDTO> createPO(@Valid @RequestBody PurchaseOrderDTO dto) {
        log.info("Creating a new Purchase Order for Vendor ID: {}", dto.getVendorId());
        return new ResponseEntity<>(poService.createPO(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderDTO> getPOById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(poService.getPOById(id));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<PurchaseOrderDTO> approvePO(@PathVariable("id") Long id) {
        log.info("Approving Purchase Order with ID: {}", id);
        return ResponseEntity.ok(poService.approvePO(id));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PurchaseOrderDTO> updateStatus(@PathVariable("id") Long id, @RequestParam("status") POStatus status) {
        return ResponseEntity.ok(poService.updateStatus(id, status));
    }
}
