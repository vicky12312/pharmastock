package com.pharmastock.project.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmastock.project.entity.ExpiryWatch;

@Repository
public interface ExpiryWatchRepository extends JpaRepository<ExpiryWatch, Long> {
}
