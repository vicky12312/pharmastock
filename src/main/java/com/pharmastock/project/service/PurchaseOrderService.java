package com.pharmastock.project.service;

import com.pharmastock.project.dto.PurchaseOrderDTO;
import com.pharmastock.project.entity.enums.POStatus;

public interface PurchaseOrderService {
    PurchaseOrderDTO createPO(PurchaseOrderDTO dto);
    PurchaseOrderDTO getPOById(Long id);
    PurchaseOrderDTO approvePO(Long id);
    PurchaseOrderDTO updateStatus(Long id, POStatus newStatus);
}
