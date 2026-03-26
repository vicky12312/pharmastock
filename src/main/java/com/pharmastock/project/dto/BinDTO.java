package com.pharmastock.project.dto;

import com.pharmastock.project.entity.enums.Status;
import com.pharmastock.project.entity.enums.StorageClass;

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
public class BinDTO {

    private Long binId;

    @NotNull(message = "Location ID is required")
    private Long locationId;

    @NotBlank(message = "Code is required")
    private String code;

    @NotNull(message = "Storage class is required")
    private StorageClass storageClass;

    @NotNull(message = "Quarantine status is required")
    private Boolean isQuarantine;

    @NotNull(message = "Status is required")
    private Status status;
}
