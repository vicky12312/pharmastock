package com.pharmastock.project.dto;

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
public class VendorDTO {

    private Long vendorId;

    @NotBlank(message = "Name is required")
    private String name;

    private String contactInfo;

    private Integer rating;

    @NotNull(message = "Status is required")
    private Status status;
}
