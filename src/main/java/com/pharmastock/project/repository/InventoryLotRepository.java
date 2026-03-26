package com.pharmastock.project.repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmastock.project.entity.InventoryLot;

@Repository
public interface InventoryLotRepository extends JpaRepository<InventoryLot, Long> {
    Optional<InventoryLot> findByItemItemIdAndBatchNumberAndExpiryDate(Long itemId, String batchNumber, LocalDate expiryDate);
    List<InventoryLot> findByItemItemIdOrderByExpiryDateAsc(Long itemId);
}
