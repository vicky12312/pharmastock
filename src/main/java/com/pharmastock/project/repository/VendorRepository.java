package com.pharmastock.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmastock.project.entity.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
