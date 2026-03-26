package com.pharmastock.project.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmastock.project.entity.ReplenishmentRequest;

@Repository
public interface ReplenishmentRequestRepository extends JpaRepository<ReplenishmentRequest, Long> {
}
