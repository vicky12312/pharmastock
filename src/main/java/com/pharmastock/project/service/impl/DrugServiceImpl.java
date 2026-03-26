package com.pharmastock.project.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pharmastock.project.dto.DrugDTO;
import com.pharmastock.project.entity.Drug;
import com.pharmastock.project.exception.ResourceNotFoundException;
import com.pharmastock.project.repository.DrugRepository;
import com.pharmastock.project.service.DrugService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DrugServiceImpl implements DrugService {

    private final DrugRepository drugRepository;

    @Override
    public DrugDTO createDrug(DrugDTO dto) {
        Drug drug = mapToEntity(dto);
        Drug savedDrug = drugRepository.save(drug);
        return mapToDTO(savedDrug);
    }

    @Override
    public List<DrugDTO> getAllDrugs() {
        return drugRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DrugDTO getDrugById(Long id) {
        Drug drug = drugRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Drug not found with id: " + id));
        return mapToDTO(drug);
    }

    @Override
    public DrugDTO updateDrug(Long id, DrugDTO dto) {
        Drug existingDrug = drugRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Drug not found with id: " + id));

        existingDrug.setGenericName(dto.getGenericName());
        existingDrug.setBrandName(dto.getBrandName());
        existingDrug.setStrength(dto.getStrength());
        existingDrug.setForm(dto.getForm());
        existingDrug.setAtcCode(dto.getAtcCode());
        existingDrug.setControlClass(dto.getControlClass());
        existingDrug.setStorageClass(dto.getStorageClass());
        existingDrug.setStatus(dto.getStatus());

        Drug updatedDrug = drugRepository.save(existingDrug);
        return mapToDTO(updatedDrug);
    }

    @Override
    public void deleteDrug(Long id) {
        Drug drug = drugRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Drug not found with id: " + id));
        drugRepository.delete(drug);
    }

    private Drug mapToEntity(DrugDTO dto) {
        return Drug.builder()
                .drugId(dto.getDrugId())
                .genericName(dto.getGenericName())
                .brandName(dto.getBrandName())
                .strength(dto.getStrength())
                .form(dto.getForm())
                .atcCode(dto.getAtcCode())
                .controlClass(dto.getControlClass())
                .storageClass(dto.getStorageClass())
                .status(dto.getStatus())
                .build();
    }

    private DrugDTO mapToDTO(Drug drug) {
        return DrugDTO.builder()
                .drugId(drug.getDrugId())
                .genericName(drug.getGenericName())
                .brandName(drug.getBrandName())
                .strength(drug.getStrength())
                .form(drug.getForm())
                .atcCode(drug.getAtcCode())
                .controlClass(drug.getControlClass())
                .storageClass(drug.getStorageClass())
                .status(drug.getStatus())
                .build();
    }
}
