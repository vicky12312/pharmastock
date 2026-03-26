package com.pharmastock.project.dto;

import com.pharmastock.project.entity.enums.ReviewCycle;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplenishmentRuleDTO {
    private Long ruleId;

    @NotNull(message = "Location ID required")
    private Long locationId;

    @NotNull(message = "Item ID required")
    private Long itemId;

    private Integer minLevel;
    private Integer maxLevel;
    private Integer parLevel;

    @NotNull(message = "Review cycle required")
    private ReviewCycle reviewCycle;
}
