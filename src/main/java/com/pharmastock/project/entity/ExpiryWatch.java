package com.pharmastock.project.entity;

import com.pharmastock.project.entity.enums.WatchStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
