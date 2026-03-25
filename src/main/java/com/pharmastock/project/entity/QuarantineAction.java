package com.pharmastock.project.entity;

import com.pharmastock.project.entity.enums.QAStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
