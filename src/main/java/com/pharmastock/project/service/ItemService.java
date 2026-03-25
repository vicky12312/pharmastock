package com.pharmastock.project.service;

import com.pharmastock.project.dto.ItemDTO;
import java.util.List;

public interface ItemService {
    ItemDTO createItem(ItemDTO dto);
    List<ItemDTO> getAllItems();
    ItemDTO getItemById(Long id);
    ItemDTO updateItem(Long id, ItemDTO dto);
    void deleteItem(Long id);
}
