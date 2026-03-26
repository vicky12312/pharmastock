package com.pharmastock.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmastock.project.dto.GoodsReceiptDTO;
import com.pharmastock.project.service.GRNService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/warehouse/grn")
@RequiredArgsConstructor
@Slf4j
public class GRNController {

    private final GRNService grnService;

    @PostMapping
    public ResponseEntity<GoodsReceiptDTO> createGRN(@Valid @RequestBody GoodsReceiptDTO dto) {
        log.info("Creating Goods Receipt (GRN) for PO ID: {}", dto.getPoId());
        return new ResponseEntity<>(grnService.createGRN(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoodsReceiptDTO> getGRNById(@PathVariable Long id) {
        return ResponseEntity.ok(grnService.getGRNById(id));
    }

    @PutMapping("/{id}/post")
    public ResponseEntity<Void> postGRN(@PathVariable Long id) {
        log.info("Posting GRN with ID: {} to Inventory", id);
        grnService.postGRN(id);
        return ResponseEntity.ok().build();
    }
}
