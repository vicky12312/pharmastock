package com.pharmastock.project.dto;

import com.pharmastock.project.entity.enums.Status;
import jakarta.validation.constraints.Min;
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
public class ItemDTO {

    private Long itemId;

    @NotNull(message = "Drug ID is required")
    private Long drugId;

    @NotNull(message = "Pack size is required")
    @Min(value = 1, message = "Pack size must be at least 1")
    private Integer packSize;

    @NotNull(message = "UoM ID is required")
    private Long uomId;

    @NotNull(message = "Conversion must be specified")
    private Double conversionToEach;

    @NotBlank(message = "Barcode is required")
    private String barcode;

    @NotNull(message = "Status is required")
    private Status status;
}
