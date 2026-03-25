package com.pharmastock.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "uom")
public class UoM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uomId;

    @Column(nullable = false, unique = true)
    private String code;

    private String description;
}
