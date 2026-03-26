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
public class InventoryReportDTO {
    private Long reportId;
    private String scope;
    private String metrics;
    private LocalDateTime generatedDate;
}
