package com.pharmastock.project.service.impl;

import com.pharmastock.project.dto.VendorDTO;
import com.pharmastock.project.entity.Vendor;
import com.pharmastock.project.entity.enums.Status;
import com.pharmastock.project.repository.VendorRepository;
import com.pharmastock.project.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    @Override
    public VendorDTO createVendor(VendorDTO dto) {
        Vendor vendor = Vendor.builder()
                .name(dto.getName())
                .contactInfo(dto.getContactInfo())
                .rating(dto.getRating())
                .status(dto.getStatus() != null ? dto.getStatus() : Status.ACTIVE)
                .build();
        return mapToDTO(vendorRepository.save(vendor));
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private VendorDTO mapToDTO(Vendor vendor) {
        return VendorDTO.builder()
                .vendorId(vendor.getVendorId())
                .name(vendor.getName())
                .contactInfo(vendor.getContactInfo())
                .rating(vendor.getRating())
                .status(vendor.getStatus())
                .build();
    }
}
