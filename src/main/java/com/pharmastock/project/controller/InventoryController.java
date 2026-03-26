package com.pharmastock.project.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmastock.project.dto.InventoryLotDTO;
import com.pharmastock.project.service.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/items/{itemId}/lots")
    public ResponseEntity<List<InventoryLotDTO>> getLotsForFefo(@PathVariable Long itemId) {
        return ResponseEntity.ok(inventoryService.getLotsForFefo(itemId));
    }

    @PostMapping("/expiry/watch")
    public ResponseEntity<Void> runExpiryCheck() {
        inventoryService.checkExpiryWatches();
        return ResponseEntity.ok().build();
    }
}
