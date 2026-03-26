package com.pharmastock.project.service;

import java.util.List;

import com.pharmastock.project.dto.ItemDTO;

public interface ItemService {
    ItemDTO createItem(ItemDTO dto);
    List<ItemDTO> getAllItems();
    ItemDTO getItemById(Long id);
    ItemDTO updateItem(Long id, ItemDTO dto);
    void deleteItem(Long id);
}
