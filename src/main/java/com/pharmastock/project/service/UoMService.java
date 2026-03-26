package com.pharmastock.project.service;

import java.util.List;

import com.pharmastock.project.dto.UoMDTO;

public interface UoMService {
    UoMDTO createUoM(UoMDTO dto);
    List<UoMDTO> getAllUoMs();
}
