package com.pharmastock.project.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pharmastock.project.dto.BinDTO;
import com.pharmastock.project.entity.Bin;
import com.pharmastock.project.entity.Location;
import com.pharmastock.project.entity.enums.Status;
import com.pharmastock.project.exception.ResourceNotFoundException;
import com.pharmastock.project.exception.ValidationException;
import com.pharmastock.project.repository.BinRepository;
import com.pharmastock.project.repository.LocationRepository;
import com.pharmastock.project.service.BinService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BinServiceImpl implements BinService {

    private final BinRepository binRepository;
    private final LocationRepository locationRepository;

    @Override
    public BinDTO createBin(BinDTO dto) {
        Location location = locationRepository.findById(dto.getLocationId())
                .orElseThrow(() -> new ResourceNotFoundException("Location not found"));

        if (binRepository.existsByLocationLocationIdAndCode(dto.getLocationId(), dto.getCode())) {
            throw new ValidationException("Bin code must be unique within a location");
        }

        Bin bin = Bin.builder()
                .location(location)
                .code(dto.getCode())
                .storageClass(dto.getStorageClass())
                .isQuarantine(dto.getIsQuarantine())
                .status(dto.getStatus() != null ? dto.getStatus() : Status.ACTIVE)
                .build();

        return mapToDTO(binRepository.save(bin));
    }

    @Override
    public List<BinDTO> getBinsByLocation(Long locationId) {
        return binRepository.findAll().stream()
                .filter(b -> b.getLocation().getLocationId().equals(locationId))
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private BinDTO mapToDTO(Bin bin) {
        return BinDTO.builder()
                .binId(bin.getBinId())
                .locationId(bin.getLocation().getLocationId())
                .code(bin.getCode())
                .storageClass(bin.getStorageClass())
                .isQuarantine(bin.getIsQuarantine())
                .status(bin.getStatus())
                .build();
    }
}
