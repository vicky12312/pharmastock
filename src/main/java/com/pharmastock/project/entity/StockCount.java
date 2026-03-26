package com.pharmastock.project.entity;

import java.time.LocalDateTime;

import com.pharmastock.project.entity.enums.CountCycle;
import com.pharmastock.project.entity.enums.CountStatus;

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
@Table(name = "stock_counts")
public class StockCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countId;

    @Column(nullable = false)
    private Long locationId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CountCycle cycle;

    @Column(nullable = false)
    private LocalDateTime scheduledDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CountStatus status;
}
