package com.pharmastock.project.entity;

import java.time.LocalDateTime;

import com.pharmastock.project.entity.enums.TxnType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stock_transactions")
public class StockTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long txnId;

    @Column(nullable = false)
    private Long locationId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bin_id", nullable = false)
    private Bin bin;

    @ManyToOne(optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne(optional = false)
    @JoinColumn(name = "lot_id", nullable = false)
    private InventoryLot lot;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TxnType txnType;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private LocalDateTime txnDate;

    @Column(nullable = false)
    private String referenceId;

    private String notes;
}
