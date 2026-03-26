package com.pharmastock.project.dto;

import java.time.LocalDateTime;

import com.pharmastock.project.entity.enums.QAStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuarantineActionDTO {
    private Long qaId;
    private Long lotId;
    private LocalDateTime quarantineDate;
    private String reason;
    private LocalDateTime releasedDate;
    private QAStatus status;
}
