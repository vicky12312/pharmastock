package com.pharmastock.project.service;
import com.pharmastock.project.dto.StockCountDTO;
public interface AuditService {
    StockCountDTO createCount(StockCountDTO dto);
    void startCount(Long countId);
    void postCount(Long countId);
    void approveAdjustment(Long adjustmentId, String approver);
}
