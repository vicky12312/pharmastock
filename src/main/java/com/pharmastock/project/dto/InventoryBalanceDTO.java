package com.pharmastock.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryBalanceDTO {

    private Long balanceId;
    private Long locationId;
    private Long binId;
    private Long itemId;
    private Long lotId;
    private Integer quantityOnHand;
    private Integer reservedQty;
}
