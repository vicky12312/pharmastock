package com.pharmastock.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmastock.project.dto.DispenseRefDTO;
import com.pharmastock.project.service.DispenseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/dispense")
@RequiredArgsConstructor
@Slf4j
public class DispenseController {

    private final DispenseService dispenseService;

    @PostMapping
    public ResponseEntity<DispenseRefDTO> dispenseItem(@Valid @RequestBody DispenseRefDTO dto) {
        log.info("Dispensing item(s) from Location: {} to Destination: {}", dto.getLocationId(), dto.getDestination());
        return new ResponseEntity<>(dispenseService.dispenseItem(dto), HttpStatus.CREATED);
    }
}
