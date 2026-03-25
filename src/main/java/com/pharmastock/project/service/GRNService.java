package com.pharmastock.project.service;

import com.pharmastock.project.dto.GoodsReceiptDTO;

public interface GRNService {
    GoodsReceiptDTO createGRN(GoodsReceiptDTO dto);
    GoodsReceiptDTO getGRNById(Long id);
    void postGRN(Long id);
}
