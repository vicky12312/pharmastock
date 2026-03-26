package com.pharmastock.project.dto;

import java.time.LocalDateTime;

import com.pharmastock.project.entity.enums.ColdChainStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColdChainLogDTO {
    private Long logId;
    private Long locationId;
    private String sensorId;
    private LocalDateTime timestamp;
    private Double temperatureC;
    private ColdChainStatus status;
}
