package com.pharmastock.project.entity;

import java.time.LocalDateTime;

import com.pharmastock.project.entity.enums.ColdChainStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
