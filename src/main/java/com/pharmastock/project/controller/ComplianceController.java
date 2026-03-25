package com.pharmastock.project.controller;

import com.pharmastock.project.dto.ColdChainLogDTO;
import com.pharmastock.project.dto.RecallNoticeDTO;
import com.pharmastock.project.dto.QuarantineActionDTO;
import com.pharmastock.project.service.ComplianceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/compliance")
@RequiredArgsConstructor
public class ComplianceController {

    private final ComplianceService complianceService;

    @PostMapping("/cold-chain")
    public ResponseEntity<ColdChainLogDTO> logColdChain(@Valid @RequestBody ColdChainLogDTO dto) {
        return new ResponseEntity<>(complianceService.logColdChain(dto), HttpStatus.CREATED);
    }

    @PostMapping("/recalls")
    public ResponseEntity<RecallNoticeDTO> createRecall(@Valid @RequestBody RecallNoticeDTO dto) {
        return new ResponseEntity<>(complianceService.createRecall(dto), HttpStatus.CREATED);
    }

    @PostMapping("/quarantine/{lotId}")
    public ResponseEntity<QuarantineActionDTO> quarantineLot(@PathVariable Long lotId, @RequestParam String reason) {
        return new ResponseEntity<>(complianceService.quarantineLot(lotId, reason), HttpStatus.CREATED);
    }

    @PutMapping("/quarantine/{qaId}/release")
    public ResponseEntity<Void> releaseQuarantine(@PathVariable Long qaId) {
        complianceService.releaseQuarantine(qaId);
        return ResponseEntity.ok().build();
    }
}
