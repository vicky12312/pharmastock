package com.pharmastock.project.entity;

import com.pharmastock.project.entity.enums.ColdChainStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cold_chain_logs")
public class ColdChainLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @Column(nullable = false)
    private Long locationId;

    @Column(nullable = false)
    private String sensorId;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private Double temperatureC;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ColdChainStatus status;
}
