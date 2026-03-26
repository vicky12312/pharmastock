package com.pharmastock.project.entity;

import java.time.LocalDateTime;

import com.pharmastock.project.entity.enums.DispenseDestination;
import com.pharmastock.project.entity.enums.DispenseStatus;

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
@Table(name = "dispenses")
public class DispenseRef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dispenseId;

    @Column(nullable = false)
    private Long locationId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne(optional = false)
    @JoinColumn(name = "lot_id", nullable = false)
    private InventoryLot lot;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private LocalDateTime dispenseDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DispenseDestination destination;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DispenseStatus status;
}
