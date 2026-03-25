package com.pharmastock.project.dto;

import com.pharmastock.project.entity.enums.TxnType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockTransactionDTO {

    private Long txnId;
    private Long locationId;
    private Long binId;
    private Long itemId;
    private Long lotId;
    private TxnType txnType;
    private Integer quantity;
    private LocalDateTime txnDate;
    private String referenceId;
    private String notes;
}
