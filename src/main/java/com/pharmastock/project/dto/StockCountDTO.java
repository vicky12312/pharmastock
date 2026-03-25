package com.pharmastock.project.dto;

import com.pharmastock.project.entity.enums.CountCycle;
import com.pharmastock.project.entity.enums.CountStatus;
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
public class StockCountDTO {
    private Long countId;
    private Long locationId;
    private CountCycle cycle;
    private LocalDateTime scheduledDate;
    private CountStatus status;
    private List<StockCountItemDTO> items;
}
