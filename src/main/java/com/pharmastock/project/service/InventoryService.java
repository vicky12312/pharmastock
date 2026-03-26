package com.pharmastock.project.service;

import java.util.List;

import com.pharmastock.project.dto.InventoryLotDTO;

public interface InventoryService {
    List<InventoryLotDTO> getLotsForFefo(Long itemId);
    void checkExpiryWatches();
}
