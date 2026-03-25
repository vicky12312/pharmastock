package com.pharmastock.project.repository;
import com.pharmastock.project.entity.QuarantineAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuarantineActionRepository extends JpaRepository<QuarantineAction, Long> {
    Long deleteByLotLotId(Long lotId);
}
