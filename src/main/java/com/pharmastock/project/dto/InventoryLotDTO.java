package com.pharmastock.project.dto;

import com.pharmastock.project.entity.enums.LotStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
