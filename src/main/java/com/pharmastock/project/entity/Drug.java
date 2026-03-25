package com.pharmastock.project.entity;

import com.pharmastock.project.entity.enums.ControlClass;
import com.pharmastock.project.entity.enums.Form;
import com.pharmastock.project.entity.enums.Status;
import com.pharmastock.project.entity.enums.StorageClass;
import jakarta.persistence.*;
import lombok.*;

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
