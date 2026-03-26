package com.pharmastock.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "uom")
public class UoM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uomId;

    @Column(nullable = false, unique = true)
    private String code;

    private String description;
}
