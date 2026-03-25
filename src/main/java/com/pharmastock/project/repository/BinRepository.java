package com.pharmastock.project.repository;

import com.pharmastock.project.entity.Bin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BinRepository extends JpaRepository<Bin, Long> {
    boolean existsByLocationLocationIdAndCode(Long locationId, String code);
}
