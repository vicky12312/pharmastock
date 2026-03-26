package com.pharmastock.project.dto;

import java.time.LocalDate;

import com.pharmastock.project.entity.enums.WatchStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpiryWatchDTO {

    private Long watchId;
    private Long lotId;
    private Integer daysToExpire;
    private LocalDate flagDate;
    private WatchStatus status;
}
