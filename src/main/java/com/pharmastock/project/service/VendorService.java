package com.pharmastock.project.service;

import java.util.List;

import com.pharmastock.project.dto.VendorDTO;

public interface VendorService {
    VendorDTO createVendor(VendorDTO dto);
    List<VendorDTO> getAllVendors();
}
