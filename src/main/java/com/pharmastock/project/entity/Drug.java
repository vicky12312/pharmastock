package com.pharmastock.project.entity;

import com.pharmastock.project.entity.enums.ControlClass;
import com.pharmastock.project.entity.enums.Form;
import com.pharmastock.project.entity.enums.Status;
import com.pharmastock.project.entity.enums.StorageClass;

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
@Table(name = "drugs")
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long drugId;

    @Column(nullable = false)
    private String genericName;

    private String brandName;

    private String strength;

    @Enumerated(EnumType.STRING)
    private Form form;

    private String atcCode;

    @Enumerated(EnumType.STRING)
    private ControlClass controlClass;

    @Enumerated(EnumType.STRING)
    private StorageClass storageClass;

    @Enumerated(EnumType.STRING)
    private Status status;
}
