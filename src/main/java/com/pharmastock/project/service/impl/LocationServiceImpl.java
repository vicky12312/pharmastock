package com.pharmastock.project.service.impl;

import com.pharmastock.project.dto.LocationDTO;
import com.pharmastock.project.entity.Location;
import com.pharmastock.project.entity.enums.Status;
import com.pharmastock.project.exception.ResourceNotFoundException;
import com.pharmastock.project.repository.LocationRepository;
import com.pharmastock.project.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    public LocationDTO createLocation(LocationDTO dto) {
        Location parent = null;
        if (dto.getParentLocationId() != null) {
            parent = locationRepository.findById(dto.getParentLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent location not found"));
        }

        Location location = Location.builder()
                .name(dto.getName())
                .type(dto.getType())
                .parentLocation(parent)
                .status(dto.getStatus() != null ? dto.getStatus() : Status.ACTIVE)
                .build();

        return mapToDTO(locationRepository.save(location));
    }

    @Override
    public List<LocationDTO> getAllLocations() {
        return locationRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private LocationDTO mapToDTO(Location location) {
        return LocationDTO.builder()
                .locationId(location.getLocationId())
                .name(location.getName())
                .type(location.getType())
                .parentLocationId(location.getParentLocation() != null ? location.getParentLocation().getLocationId() : null)
                .status(location.getStatus())
                .build();
    }
}
