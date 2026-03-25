package com.pharmastock.project.controller;

import com.pharmastock.project.dto.InventoryLotDTO;
import com.pharmastock.project.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
