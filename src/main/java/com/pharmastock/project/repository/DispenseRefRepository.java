package com.pharmastock.project.repository;
import com.pharmastock.project.entity.DispenseRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispenseRefRepository extends JpaRepository<DispenseRef, Long> {
}
