package com.pharmastock.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmastock.project.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsByBarcode(String barcode);
}
