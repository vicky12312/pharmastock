package com.pharmastock.project.controller;

import com.pharmastock.project.dto.DispenseRefDTO;
import com.pharmastock.project.service.DispenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dispense")
@RequiredArgsConstructor
public class DispenseController {

    private final DispenseService dispenseService;

    @PostMapping
    public ResponseEntity<DispenseRefDTO> dispenseItem(@Valid @RequestBody DispenseRefDTO dto) {
        return new ResponseEntity<>(dispenseService.dispenseItem(dto), HttpStatus.CREATED);
    }
}
