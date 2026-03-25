package com.pharmastock.project.dto;

import com.pharmastock.project.entity.enums.GRNStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsReceiptDTO {

    private Long grnId;

    @NotNull(message = "Purchase Order ID is required")
    private Long poId;

    private String receivedBy;

    private LocalDateTime receivedDate;

    private GRNStatus status;

    private List<GRNItemDTO> items;
}
