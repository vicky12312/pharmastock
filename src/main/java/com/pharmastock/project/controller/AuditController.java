package com.pharmastock.project.controller;

import com.pharmastock.project.dto.StockCountDTO;
import com.pharmastock.project.service.AuditService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/audit")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService auditService;

    @PostMapping("/count")
    public ResponseEntity<StockCountDTO> createCount(@Valid @RequestBody StockCountDTO dto) {
        return new ResponseEntity<>(auditService.createCount(dto), HttpStatus.CREATED);
    }

    @PutMapping("/count/{id}/start")
    public ResponseEntity<Void> startCount(@PathVariable Long id) {
        auditService.startCount(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/count/{id}/post")
    public ResponseEntity<Void> postCount(@PathVariable Long id) {
        auditService.postCount(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/adjustment/{id}/approve")
    public ResponseEntity<Void> approveAdjustment(@PathVariable Long id, @RequestParam String approver) {
        auditService.approveAdjustment(id, approver);
        return ResponseEntity.ok().build();
    }
}
