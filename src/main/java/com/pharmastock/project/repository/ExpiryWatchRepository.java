package com.pharmastock.project.repository;
import com.pharmastock.project.entity.ExpiryWatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpiryWatchRepository extends JpaRepository<ExpiryWatch, Long> {
}
