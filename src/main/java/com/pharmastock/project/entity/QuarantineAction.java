package com.pharmastock.project.entity;

import java.time.LocalDateTime;

import com.pharmastock.project.entity.enums.QAStatus;

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
@Table(name = "quarantine_actions")
public class QuarantineAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qaId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "lot_id", nullable = false)
    private InventoryLot lot;

    @Column(nullable = false)
    private LocalDateTime quarantineDate;

    @Column(nullable = false)
    private String reason;

    private LocalDateTime releasedDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QAStatus status;
}
