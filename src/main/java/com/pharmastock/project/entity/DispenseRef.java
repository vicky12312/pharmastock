package com.pharmastock.project.entity;

import com.pharmastock.project.entity.enums.DispenseDestination;
import com.pharmastock.project.entity.enums.DispenseStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
