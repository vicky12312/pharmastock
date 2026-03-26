package com.pharmastock.project.service;

import java.util.List;

import com.pharmastock.project.dto.LocationDTO;

public interface LocationService {
    LocationDTO createLocation(LocationDTO dto);
    List<LocationDTO> getAllLocations();
}
