package com.pharmastock.project.repository;
import com.pharmastock.project.entity.InventoryReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryReportRepository extends JpaRepository<InventoryReport, Long> {}
