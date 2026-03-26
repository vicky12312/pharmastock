package com.pharmastock.project.service;

import java.util.List;

import com.pharmastock.project.dto.DrugDTO;

public interface DrugService {
    DrugDTO createDrug(DrugDTO dto);
    List<DrugDTO> getAllDrugs();
    DrugDTO getDrugById(Long id);
    DrugDTO updateDrug(Long id, DrugDTO dto);
    void deleteDrug(Long id);
}
