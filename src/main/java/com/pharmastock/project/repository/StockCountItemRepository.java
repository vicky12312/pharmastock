package com.pharmastock.project.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmastock.project.entity.StockCountItem;

@Repository
public interface StockCountItemRepository extends JpaRepository<StockCountItem, Long> {
    List<StockCountItem> findByStockCountCountId(Long countId);
}
