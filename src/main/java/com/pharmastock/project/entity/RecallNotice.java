package com.pharmastock.project.entity;

import java.time.LocalDateTime;

import com.pharmastock.project.entity.enums.RecallAction;
import com.pharmastock.project.entity.enums.RecallStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recall_notices")
public class RecallNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recallId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "drug_id", nullable = false)
    private Drug drug;

    @Column(nullable = false)
    private LocalDateTime noticeDate;

    @Column(nullable = false)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RecallAction action;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RecallStatus status;
}
