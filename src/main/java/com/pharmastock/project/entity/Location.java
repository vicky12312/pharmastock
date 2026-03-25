package com.pharmastock.project.entity;

import com.pharmastock.project.entity.enums.LocationType;
import com.pharmastock.project.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LocationType type;

    @ManyToOne
    @JoinColumn(name = "parent_location_id")
    private Location parentLocation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}
