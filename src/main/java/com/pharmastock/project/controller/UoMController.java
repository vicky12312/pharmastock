package com.pharmastock.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmastock.project.dto.UoMDTO;
import com.pharmastock.project.service.UoMService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/formulary/uom")
@RequiredArgsConstructor
public class UoMController {

    private final UoMService uomService;

    @PostMapping
    public ResponseEntity<UoMDTO> createUoM(@Valid @RequestBody UoMDTO dto) {
        return new ResponseEntity<>(uomService.createUoM(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UoMDTO>> getAllUoMs() {
        return ResponseEntity.ok(uomService.getAllUoMs());
    }
}
