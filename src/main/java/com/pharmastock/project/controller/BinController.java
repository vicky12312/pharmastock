package com.pharmastock.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmastock.project.dto.BinDTO;
import com.pharmastock.project.service.BinService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/bins")
@RequiredArgsConstructor
public class BinController {

    private final BinService binService;

    @PostMapping
    public ResponseEntity<BinDTO> createBin(@Valid @RequestBody BinDTO dto) {
        return new ResponseEntity<>(binService.createBin(dto), HttpStatus.CREATED);
    }

    @GetMapping("/location/{locationId}")
    public ResponseEntity<List<BinDTO>> getBinsByLocation(@PathVariable Long locationId) {
        return ResponseEntity.ok(binService.getBinsByLocation(locationId));
    }
}
