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

import com.pharmastock.project.dto.TransferOrderDTO;
import com.pharmastock.project.service.TransferService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/warehouse/transfers")
@RequiredArgsConstructor
@Slf4j
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public ResponseEntity<TransferOrderDTO> createTransfer(@Valid @RequestBody TransferOrderDTO dto) {
        log.info("Creating a new Transfer Order from Location: {} to Location: {}", dto.getFromLocationId(), dto.getToLocationId());
        return new ResponseEntity<>(transferService.createTransfer(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferOrderDTO> getTransfer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(transferService.getTransfer(id));
    }

    @PutMapping("/{id}/pick")
    public ResponseEntity<Void> pickTransfer(@PathVariable("id") Long id) {
        log.info("Picking Items for Transfer Order ID: {}", id);
        transferService.pickTransfer(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/ship")
    public ResponseEntity<Void> shipTransfer(@PathVariable("id") Long id) {
        log.info("Shipping Transfer Order ID: {}", id);
        transferService.shipTransfer(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/receive")
    public ResponseEntity<Void> receiveTransfer(@PathVariable("id") Long id) {
        log.info("Receiving Transfer Order ID: {} at destination", id);
        transferService.receiveTransfer(id);
        return ResponseEntity.ok().build();
    }
}
