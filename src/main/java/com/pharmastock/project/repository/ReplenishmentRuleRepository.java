package com.pharmastock.project.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmastock.project.entity.ReplenishmentRule;

@Repository
public interface ReplenishmentRuleRepository extends JpaRepository<ReplenishmentRule, Long> {
}
