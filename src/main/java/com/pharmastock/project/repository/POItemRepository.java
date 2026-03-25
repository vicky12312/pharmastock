package com.pharmastock.project.repository;
import com.pharmastock.project.entity.POItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface POItemRepository extends JpaRepository<POItem, Long> {
}
