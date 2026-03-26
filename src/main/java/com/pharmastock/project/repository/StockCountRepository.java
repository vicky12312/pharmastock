package com.pharmastock.project.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmastock.project.entity.StockCount;

@Repository
public interface StockCountRepository extends JpaRepository<StockCount, Long> {}
