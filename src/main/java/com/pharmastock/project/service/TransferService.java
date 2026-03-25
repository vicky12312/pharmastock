package com.pharmastock.project.service;

import com.pharmastock.project.dto.TransferOrderDTO;

public interface TransferService {
    TransferOrderDTO createTransfer(TransferOrderDTO dto);
    TransferOrderDTO getTransfer(Long id);
    void pickTransfer(Long toId);
    void shipTransfer(Long toId);
    void receiveTransfer(Long toId);
}
