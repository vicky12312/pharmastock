package com.pharmastock.project.service;

import com.pharmastock.project.dto.LocationDTO;
import java.util.List;

public interface LocationService {
    LocationDTO createLocation(LocationDTO dto);
    List<LocationDTO> getAllLocations();
}
