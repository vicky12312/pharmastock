package com.pharmastock.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmastock.project.entity.UoM;

@Repository
public interface UoMRepository extends JpaRepository<UoM, Long> {
}
