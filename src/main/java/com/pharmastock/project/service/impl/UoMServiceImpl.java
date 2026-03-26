package com.pharmastock.project.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pharmastock.project.dto.UoMDTO;
import com.pharmastock.project.entity.UoM;
import com.pharmastock.project.repository.UoMRepository;
import com.pharmastock.project.service.UoMService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UoMServiceImpl implements UoMService {

    private final UoMRepository uomRepository;

    @Override
    public UoMDTO createUoM(UoMDTO dto) {
        UoM uom = UoM.builder()
                .code(dto.getCode())
                .description(dto.getDescription())
                .build();
        UoM saved = uomRepository.save(uom);
        return mapToDTO(saved);
    }

    @Override
    public List<UoMDTO> getAllUoMs() {
        return uomRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private UoMDTO mapToDTO(UoM uom) {
        return UoMDTO.builder()
                .uomId(uom.getUomId())
                .code(uom.getCode())
                .description(uom.getDescription())
                .build();
    }
}
