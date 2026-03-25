package com.pharmastock.project.controller;

import com.pharmastock.project.dto.PurchaseOrderDTO;
import com.pharmastock.project.entity.enums.POStatus;
import com.pharmastock.project.service.PurchaseOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/procurement/po")
@RequiredArgsConstructor
public class PurchaseOrderController {

    private final PurchaseOrderService poService;

    @PostMapping
    public ResponseEntity<PurchaseOrderDTO> createPO(@Valid @RequestBody PurchaseOrderDTO dto) {
        return new ResponseEntity<>(poService.createPO(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderDTO> getPOById(@PathVariable Long id) {
        return ResponseEntity.ok(poService.getPOById(id));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<PurchaseOrderDTO> approvePO(@PathVariable Long id) {
        return ResponseEntity.ok(poService.approvePO(id));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PurchaseOrderDTO> updateStatus(@PathVariable Long id, @RequestParam POStatus status) {
        return ResponseEntity.ok(poService.updateStatus(id, status));
    }
}
