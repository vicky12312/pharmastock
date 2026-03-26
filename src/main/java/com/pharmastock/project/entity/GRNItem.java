package com.pharmastock.project.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
