package com.pharmastock.project.service;

import java.util.List;

import com.pharmastock.project.dto.BinDTO;

public interface BinService {
    BinDTO createBin(BinDTO dto);
    List<BinDTO> getBinsByLocation(Long locationId);
}
