package com.pharmastock.project.entity;

import java.time.LocalDate;

import com.pharmastock.project.entity.enums.WatchStatus;

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
@Table(name = "expiry_watch")
public class ExpiryWatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long watchId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "lot_id", nullable = false)
    private InventoryLot lot;

    @Column(nullable = false)
    private Integer daysToExpire;

    @Column(nullable = false)
    private LocalDate flagDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WatchStatus status;
}
