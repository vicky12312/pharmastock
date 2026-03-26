package com.pharmastock.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmastock.project.dto.DrugDTO;
import com.pharmastock.project.service.DrugService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/formulary/drugs")
@RequiredArgsConstructor
public class DrugController {

    private final DrugService drugService;

    @PostMapping
    public ResponseEntity<DrugDTO> createDrug(@Valid @RequestBody DrugDTO dto) {
        return new ResponseEntity<>(drugService.createDrug(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DrugDTO>> getAllDrugs() {
        return ResponseEntity.ok(drugService.getAllDrugs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrugDTO> getDrugById(@PathVariable Long id) {
        return ResponseEntity.ok(drugService.getDrugById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DrugDTO> updateDrug(@PathVariable Long id, @Valid @RequestBody DrugDTO dto) {
        return ResponseEntity.ok(drugService.updateDrug(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrug(@PathVariable Long id) {
        drugService.deleteDrug(id);
        return ResponseEntity.noContent().build();
    }
}
