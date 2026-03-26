package com.pharmastock.project.dto;

import java.time.LocalDateTime;

import com.pharmastock.project.entity.enums.RecallAction;
import com.pharmastock.project.entity.enums.RecallStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecallNoticeDTO {
    private Long recallId;
    private Long drugId;
    private LocalDateTime noticeDate;
    private String reason;
    private RecallAction action;
    private RecallStatus status;
}
