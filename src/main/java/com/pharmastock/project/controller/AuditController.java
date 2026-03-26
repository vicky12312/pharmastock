package com.pharmastock.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pharmastock.project.dto.StockCountDTO;
import com.pharmastock.project.service.AuditService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/audit")
@RequiredArgsConstructor
@Slf4j
public class AuditController {

    private final AuditService auditService;

    @PostMapping("/count")
    public ResponseEntity<StockCountDTO> createCount(@Valid @RequestBody StockCountDTO dto) {
        log.info("Creating a new Cycle Count for Location: {}", dto.getLocationId());
        return new ResponseEntity<>(auditService.createCount(dto), HttpStatus.CREATED);
    }

    @PutMapping("/count/{id}/start")
    public ResponseEntity<Void> startCount(@PathVariable("id") Long id) {
        log.info("Starting Cycle Count ID: {}", id);
        auditService.startCount(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/count/{id}/post")
    public ResponseEntity<Void> postCount(@PathVariable("id") Long id) {
        log.info("Posting Cycle Count ID: {}", id);
        auditService.postCount(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/adjustment/{id}/approve")
    public ResponseEntity<Void> approveAdjustment(@PathVariable("id") Long id, @RequestParam("approver") String approver) {
        log.info("Approving Stock Adjustment ID: {} by User: {}", id, approver);
        auditService.approveAdjustment(id, approver);
        return ResponseEntity.ok().build();
    }
}
