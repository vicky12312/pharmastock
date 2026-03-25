package com.pharmastock.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GRNItemDTO {

    private Long grnItemId;

    @NotNull(message = "PO Item ID is required")
    private Long poItemId;

    @NotNull(message = "Item ID is required")
    private Long itemId;

    @NotBlank(message = "Batch number is required")
    private String batchNumber;

    @NotNull(message = "Expiry date is required")
    private LocalDate expiryDate;

    @NotNull(message = "Received quantity is required")
    private Integer receivedQty;

    @NotNull(message = "Accepted quantity is required")
    private Integer acceptedQty;

    @NotNull(message = "Rejected quantity is required")
    private Integer rejectedQty;

    private String reason;
}
