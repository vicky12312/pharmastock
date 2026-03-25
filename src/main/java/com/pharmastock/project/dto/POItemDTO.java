package com.pharmastock.project.dto;

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
public class POItemDTO {

    private Long poItemId;

    @NotNull(message = "Item ID is required")
    private Long itemId;

    @NotNull(message = "Ordered quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer orderedQty;

    @NotNull(message = "Unit price is required")
    @Min(value = 0, message = "Unit price cannot be negative")
    private Double unitPrice;

    private Double taxPct;
}
