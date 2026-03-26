package com.pharmastock.project.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmastock.project.entity.GRNItem;

@Repository
public interface GRNItemRepository extends JpaRepository<GRNItem, Long> {
    List<GRNItem> findByGrnGrnId(Long grnId);
}
