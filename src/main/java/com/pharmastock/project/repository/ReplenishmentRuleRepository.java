package com.pharmastock.project.repository;
import com.pharmastock.project.entity.ReplenishmentRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplenishmentRuleRepository extends JpaRepository<ReplenishmentRule, Long> {
}
