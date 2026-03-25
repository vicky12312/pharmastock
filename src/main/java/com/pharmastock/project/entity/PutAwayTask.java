package com.pharmastock.project.entity;

import com.pharmastock.project.entity.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "put_away_tasks")
public class PutAwayTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "grn_item_id", nullable = false)
    private GRNItem grnItem;

    @ManyToOne(optional = false)
    @JoinColumn(name = "target_bin_id", nullable = false)
    private Bin targetBin;

    @Column(nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;
}
