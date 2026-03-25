package com.pharmastock.project.repository;
import com.pharmastock.project.entity.ColdChainLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColdChainLogRepository extends JpaRepository<ColdChainLog, Long> {}
