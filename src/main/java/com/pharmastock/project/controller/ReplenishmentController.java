package com.pharmastock.project.controller;

import com.pharmastock.project.dto.ReplenishmentRuleDTO;
import com.pharmastock.project.service.ReplenishmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/replenishment")
@RequiredArgsConstructor
public class ReplenishmentController {

    private final ReplenishmentService replenishmentService;

    @PostMapping("/rules")
    public ResponseEntity<ReplenishmentRuleDTO> createRule(@Valid @RequestBody ReplenishmentRuleDTO dto) {
        return new ResponseEntity<>(replenishmentService.createRule(dto), HttpStatus.CREATED);
    }

    @GetMapping("/suggestions")
    public ResponseEntity<Void> generateSuggestions() {
        replenishmentService.generateSuggestions();
        return ResponseEntity.ok().build();
    }
}
