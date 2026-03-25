package com.pharmastock.project.entity;

import com.pharmastock.project.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "drug_id", nullable = false)
    private Drug drug;

    @Column(nullable = false)
    private Integer packSize;

    @ManyToOne(optional = false)
    @JoinColumn(name = "uom_id", nullable = false)
    private UoM uom;

    @Column(nullable = false)
    private Double conversionToEach;

    @Column(nullable = false, unique = true)
    private String barcode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}
