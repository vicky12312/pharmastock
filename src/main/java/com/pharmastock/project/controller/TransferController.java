package com.pharmastock.project.controller;

import com.pharmastock.project.dto.TransferOrderDTO;
import com.pharmastock.project.service.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/warehouse/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public ResponseEntity<TransferOrderDTO> createTransfer(@Valid @RequestBody TransferOrderDTO dto) {
        return new ResponseEntity<>(transferService.createTransfer(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferOrderDTO> getTransfer(@PathVariable Long id) {
        return ResponseEntity.ok(transferService.getTransfer(id));
    }

    @PutMapping("/{id}/pick")
    public ResponseEntity<Void> pickTransfer(@PathVariable Long id) {
        transferService.pickTransfer(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/ship")
    public ResponseEntity<Void> shipTransfer(@PathVariable Long id) {
        transferService.shipTransfer(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/receive")
    public ResponseEntity<Void> receiveTransfer(@PathVariable Long id) {
        transferService.receiveTransfer(id);
        return ResponseEntity.ok().build();
    }
}
