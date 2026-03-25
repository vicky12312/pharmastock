package com.pharmastock.project.service;

import com.pharmastock.project.dto.VendorDTO;
import java.util.List;

public interface VendorService {
    VendorDTO createVendor(VendorDTO dto);
    List<VendorDTO> getAllVendors();
}
