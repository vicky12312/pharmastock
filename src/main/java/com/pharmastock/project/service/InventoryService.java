package com.pharmastock.project.service;

import com.pharmastock.project.dto.InventoryLotDTO;
import java.util.List;

public interface InventoryService {
    List<InventoryLotDTO> getLotsForFefo(Long itemId);
    void checkExpiryWatches();
}
