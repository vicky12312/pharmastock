package com.pharmastock.project.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmastock.project.entity.ColdChainLog;

@Repository
public interface ColdChainLogRepository extends JpaRepository<ColdChainLog, Long> {}
