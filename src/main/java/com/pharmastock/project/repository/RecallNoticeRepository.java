package com.pharmastock.project.repository;
import com.pharmastock.project.entity.RecallNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecallNoticeRepository extends JpaRepository<RecallNotice, Long> {}
