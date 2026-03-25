package com.pharmastock.project.entity;

import com.pharmastock.project.entity.enums.ReviewCycle;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "replenishment_rules")
public class ReplenishmentRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ruleId;

    @Column(nullable = false)
    private Long locationId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private Integer minLevel;

    @Column(nullable = false)
    private Integer maxLevel;

    @Column(nullable = false)
    private Integer parLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReviewCycle reviewCycle;
}
