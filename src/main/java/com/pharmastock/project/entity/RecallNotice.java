package com.pharmastock.project.entity;

import com.pharmastock.project.entity.enums.RecallAction;
import com.pharmastock.project.entity.enums.RecallStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
