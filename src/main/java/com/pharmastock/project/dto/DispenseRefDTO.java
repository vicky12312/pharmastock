package com.pharmastock.project.dto;

import java.time.LocalDateTime;

import com.pharmastock.project.entity.enums.DispenseDestination;
import com.pharmastock.project.entity.enums.DispenseStatus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DispenseRefDTO {
    private Long dispenseId;

    @NotNull(message = "Location ID required")
    private Long locationId;

    @NotNull(message = "Item ID required")
    private Long itemId;

    private Long lotId;

    @NotNull(message = "Quantity required")
    @Min(value = 1)
    private Integer quantity;

    private LocalDateTime dispenseDate;

    @NotNull(message = "Destination required")
    private DispenseDestination destination;

    private DispenseStatus status;
}
