package com.pharmastock.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockCountItemDTO {
    private Long countItemId;
    private Long countId;
    private Long binId;
    private Long itemId;
    private Long lotId;
    private Integer systemQty;
    private Integer countedQty;
    private Integer variance;
    private String reasonCode;
}
