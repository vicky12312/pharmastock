package com.pharmastock.project.controller;

import com.pharmastock.project.dto.GoodsReceiptDTO;
import com.pharmastock.project.service.GRNService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/warehouse/grn")
@RequiredArgsConstructor
public class GRNController {

    private final GRNService grnService;

    @PostMapping
    public ResponseEntity<GoodsReceiptDTO> createGRN(@Valid @RequestBody GoodsReceiptDTO dto) {
        return new ResponseEntity<>(grnService.createGRN(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoodsReceiptDTO> getGRNById(@PathVariable Long id) {
        return ResponseEntity.ok(grnService.getGRNById(id));
    }

    @PutMapping("/{id}/post")
    public ResponseEntity<Void> postGRN(@PathVariable Long id) {
        grnService.postGRN(id);
        return ResponseEntity.ok().build();
    }
}
