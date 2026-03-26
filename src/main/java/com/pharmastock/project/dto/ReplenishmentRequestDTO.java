package com.pharmastock.project.dto;

import java.time.LocalDateTime;

import com.pharmastock.project.entity.enums.ReqStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplenishmentRequestDTO {
    private Long reqId;
    private Long locationId;
    private Long itemId;
    private Integer suggestedQty;
    private LocalDateTime createdDate;
    private ReqStatus status;
}
