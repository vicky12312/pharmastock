package com.pharmastock.project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockAdjustmentDTO {
    private Long adjustmentId;
    private Long locationId;
    private Long itemId;
    private Long lotId;
    private Integer quantityDelta;
    private String reason;
    private String approvedBy;
    private LocalDateTime postedDate;
}
