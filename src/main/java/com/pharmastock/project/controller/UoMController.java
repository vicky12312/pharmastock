package com.pharmastock.project.controller;

import com.pharmastock.project.dto.UoMDTO;
import com.pharmastock.project.service.UoMService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
