package com.pharmastock.project.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmastock.project.entity.RecallNotice;

@Repository
public interface RecallNoticeRepository extends JpaRepository<RecallNotice, Long> {}
