package com.pharmastock.project.dto;

import com.pharmastock.project.entity.enums.LocationType;
import com.pharmastock.project.entity.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {

    private Long locationId;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Location type is required")
    private LocationType type;

    private Long parentLocationId;

    @NotNull(message = "Status is required")
    private Status status;
}
