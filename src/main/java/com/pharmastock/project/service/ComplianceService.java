package com.pharmastock.project.service;
import com.pharmastock.project.dto.ColdChainLogDTO;
import com.pharmastock.project.dto.RecallNoticeDTO;
import com.pharmastock.project.dto.QuarantineActionDTO;

public interface ComplianceService {
    ColdChainLogDTO logColdChain(ColdChainLogDTO dto);
    RecallNoticeDTO createRecall(RecallNoticeDTO dto);
    QuarantineActionDTO quarantineLot(Long lotId, String reason);
    void releaseQuarantine(Long qaId);
}
