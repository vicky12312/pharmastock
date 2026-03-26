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
public class TransferItemDTO {
    private Long toItemId;

    @NotNull(message = "Transfer Order ID required")
    private Long toId;

    @NotNull(message = "Item ID required")
    private Long itemId;

    private Long lotId; // Null initially

    @NotNull(message = "Quantity required")
    @Min(value = 1, message = "Quantity > 0")
    private Integer quantity;
}
