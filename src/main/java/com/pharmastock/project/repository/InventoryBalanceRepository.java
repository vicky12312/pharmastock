package com.pharmastock.project.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmastock.project.entity.InventoryBalance;

@Repository
public interface InventoryBalanceRepository extends JpaRepository<InventoryBalance, Long> {
    Optional<InventoryBalance> findByLocationIdAndBinBinIdAndItemItemIdAndLotLotId(Long locationId, Long binId, Long itemId, Long lotId);
    List<InventoryBalance> findByItemItemId(Long itemId);
}
