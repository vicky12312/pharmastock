package com.pharmastock.project.service.impl;

import com.pharmastock.project.dto.ItemDTO;
import com.pharmastock.project.entity.Drug;
import com.pharmastock.project.entity.Item;
import com.pharmastock.project.entity.UoM;
import com.pharmastock.project.entity.enums.Status;
import com.pharmastock.project.exception.ResourceNotFoundException;
import com.pharmastock.project.exception.ValidationException;
import com.pharmastock.project.repository.DrugRepository;
import com.pharmastock.project.repository.ItemRepository;
import com.pharmastock.project.repository.UoMRepository;
import com.pharmastock.project.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final DrugRepository drugRepository;
    private final UoMRepository uomRepository;

    @Override
    public ItemDTO createItem(ItemDTO dto) {
        if (dto.getConversionToEach() == null || dto.getConversionToEach() <= 0) {
            throw new ValidationException("Conversion to each must be greater than 0");
        }
        if (itemRepository.existsByBarcode(dto.getBarcode())) {
            throw new ValidationException("Barcode must be unique");
        }

        Drug drug = drugRepository.findById(dto.getDrugId())
                .orElseThrow(() -> new ResourceNotFoundException("Drug not found with id: " + dto.getDrugId()));

        if (drug.getStatus() != Status.ACTIVE) {
            throw new ValidationException("Drug must be ACTIVE to assign an item");
        }

        UoM uom = uomRepository.findById(dto.getUomId())
                .orElseThrow(() -> new ResourceNotFoundException("UoM not found with id: " + dto.getUomId()));

        Item item = Item.builder()
                .drug(drug)
                .packSize(dto.getPackSize())
                .uom(uom)
                .conversionToEach(dto.getConversionToEach())
                .barcode(dto.getBarcode())
                .status(dto.getStatus() != null ? dto.getStatus() : Status.ACTIVE)
                .build();

        return mapToDTO(itemRepository.save(item));
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return itemRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ItemDTO getItemById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
        return mapToDTO(item);
    }

    @Override
    public ItemDTO updateItem(Long id, ItemDTO dto) {
        Item existingItem = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));

        if (!existingItem.getDrug().getDrugId().equals(dto.getDrugId())) {
            throw new ValidationException("Cannot modify drug ID of an existing item");
        }
        if (dto.getConversionToEach() == null || dto.getConversionToEach() <= 0) {
            throw new ValidationException("Conversion to each must be greater than 0");
        }
        if (!existingItem.getBarcode().equals(dto.getBarcode()) && itemRepository.existsByBarcode(dto.getBarcode())) {
            throw new ValidationException("Barcode must be unique");
        }

        UoM uom = uomRepository.findById(dto.getUomId())
                .orElseThrow(() -> new ResourceNotFoundException("UoM not found with id: " + dto.getUomId()));

        // In a real scenario we might check inventory before changing UoM but as per rules, assuming validation method
        
        existingItem.setPackSize(dto.getPackSize());
        existingItem.setUom(uom);
        existingItem.setConversionToEach(dto.getConversionToEach());
        existingItem.setBarcode(dto.getBarcode());
        existingItem.setStatus(dto.getStatus());

        return mapToDTO(itemRepository.save(existingItem));
    }

    @Override
    public void deleteItem(Long id) {
        Item existingItem = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
        existingItem.setStatus(Status.INACTIVE);
        itemRepository.save(existingItem);
    }

    private ItemDTO mapToDTO(Item item) {
        return ItemDTO.builder()
                .itemId(item.getItemId())
                .drugId(item.getDrug().getDrugId())
                .packSize(item.getPackSize())
                .uomId(item.getUom().getUomId())
                .conversionToEach(item.getConversionToEach())
                .barcode(item.getBarcode())
                .status(item.getStatus())
                .build();
    }
}
