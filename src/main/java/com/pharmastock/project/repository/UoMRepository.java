package com.pharmastock.project.repository;

import com.pharmastock.project.entity.UoM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UoMRepository extends JpaRepository<UoM, Long> {
}
