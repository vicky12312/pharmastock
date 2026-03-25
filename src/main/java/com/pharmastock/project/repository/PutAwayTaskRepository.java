package com.pharmastock.project.repository;
import com.pharmastock.project.entity.PutAwayTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PutAwayTaskRepository extends JpaRepository<PutAwayTask, Long> {
}
