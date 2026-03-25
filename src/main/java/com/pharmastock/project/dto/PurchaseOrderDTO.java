package com.pharmastock.project.dto;

import com.pharmastock.project.entity.enums.POStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderDTO {

    private Long poId;

    @NotNull(message = "Vendor ID is required")
    private Long vendorId;

    @NotNull(message = "Location ID is required")
    private Long locationId;

    private LocalDate orderDate;

    private LocalDate expectedDate;

    private POStatus status;

    private List<POItemDTO> items;
}
