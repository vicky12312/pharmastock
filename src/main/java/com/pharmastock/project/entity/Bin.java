package com.pharmastock.project.entity;

import com.pharmastock.project.entity.enums.Status;
import com.pharmastock.project.entity.enums.StorageClass;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "bins",
    uniqueConstraints = @UniqueConstraint(columnNames = {"location_id", "code"})
)
public class Bin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long binId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(nullable = false)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StorageClass storageClass;

    @Column(nullable = false)
    private Boolean isQuarantine;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}
