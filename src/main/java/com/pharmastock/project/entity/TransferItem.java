package com.pharmastock.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transfer_items")
public class TransferItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long toItemId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "to_id", nullable = false)
    @ToString.Exclude
    private TransferOrder transferOrder;

    @ManyToOne(optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private InventoryLot lot;

    @Column(nullable = false)
    private Integer quantity;
}
