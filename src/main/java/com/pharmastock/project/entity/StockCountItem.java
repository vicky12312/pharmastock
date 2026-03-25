package com.pharmastock.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stock_count_items")
public class StockCountItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countItemId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "count_id", nullable = false)
    @ToString.Exclude
    private StockCount stockCount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bin_id", nullable = false)
    private Bin bin;

    @ManyToOne(optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne(optional = false)
    @JoinColumn(name = "lot_id", nullable = false)
    private InventoryLot lot;

    @Column(nullable = false)
    private Integer systemQty;

    @Column(nullable = false)
    private Integer countedQty;

    @Column(nullable = false)
    private Integer variance;

    private String reasonCode;
}
