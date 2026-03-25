package com.pharmastock.project.repository;
import com.pharmastock.project.entity.ReplenishmentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplenishmentRequestRepository extends JpaRepository<ReplenishmentRequest, Long> {
}
