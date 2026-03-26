package com.pharmastock.project.dto;

import java.time.LocalDate;

import com.pharmastock.project.entity.enums.LotStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryLotDTO {

    private Long lotId;
    private Long itemId;
    private String batchNumber;
    private LocalDate expiryDate;
    private String manufacturer;
    private LotStatus status;
}
