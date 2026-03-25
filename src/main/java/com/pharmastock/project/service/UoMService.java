package com.pharmastock.project.service;

import com.pharmastock.project.dto.UoMDTO;
import java.util.List;

public interface UoMService {
    UoMDTO createUoM(UoMDTO dto);
    List<UoMDTO> getAllUoMs();
}
