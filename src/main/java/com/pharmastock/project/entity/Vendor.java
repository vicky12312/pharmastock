package com.pharmastock.project.entity;

import com.pharmastock.project.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vendors")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendorId;

    @Column(nullable = false)
    private String name;

    private String contactInfo;

    private Integer rating;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}
