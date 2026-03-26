package com.pharmastock.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmastock.project.entity.Bin;

@Repository
public interface BinRepository extends JpaRepository<Bin, Long> {
    boolean existsByLocationLocationIdAndCode(Long locationId, String code);
}
