package com.pharmastock.project.service;

import com.pharmastock.project.dto.BinDTO;
import java.util.List;

public interface BinService {
    BinDTO createBin(BinDTO dto);
    List<BinDTO> getBinsByLocation(Long locationId);
}
