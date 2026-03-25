package com.pharmastock.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "grn_items")
public class GRNItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long grnItemId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "grn_id", nullable = false)
    @ToString.Exclude
    private GoodsReceipt grn;

    @ManyToOne(optional = false)
    @JoinColumn(name = "po_item_id", nullable = false)
    private POItem poItem;

    @ManyToOne(optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private String batchNumber;

    @Column(nullable = false)
    private LocalDate expiryDate;

    @Column(nullable = false)
    private Integer receivedQty;

    @Column(nullable = false)
    private Integer acceptedQty;

    @Column(nullable = false)
    private Integer rejectedQty;

    private String reason;
}
