package com.pharmastock.project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pharmastock.project.dto.POItemDTO;
import com.pharmastock.project.dto.PurchaseOrderDTO;
import com.pharmastock.project.entity.Item;
import com.pharmastock.project.entity.Location;
import com.pharmastock.project.entity.POItem;
import com.pharmastock.project.entity.PurchaseOrder;
import com.pharmastock.project.entity.Vendor;
import com.pharmastock.project.entity.enums.POStatus;
import com.pharmastock.project.entity.enums.Status;
import com.pharmastock.project.exception.ResourceNotFoundException;
import com.pharmastock.project.exception.ValidationException;
import com.pharmastock.project.repository.ItemRepository;
import com.pharmastock.project.repository.LocationRepository;
import com.pharmastock.project.repository.PurchaseOrderRepository;
import com.pharmastock.project.repository.VendorRepository;
import com.pharmastock.project.service.PurchaseOrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository poRepository;
    private final VendorRepository vendorRepository;
    private final LocationRepository locationRepository;
    private final ItemRepository itemRepository;

    @Override
    public PurchaseOrderDTO createPO(PurchaseOrderDTO dto) {
        Vendor vendor = vendorRepository.findById(dto.getVendorId())
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));

        if (vendor.getStatus() != Status.ACTIVE) {
            throw new ValidationException("Vendor must be ACTIVE to create a PO");
        }

        Location location = locationRepository.findById(dto.getLocationId())
                .orElseThrow(() -> new ResourceNotFoundException("Location not found"));

        PurchaseOrder po = PurchaseOrder.builder()
                .vendor(vendor)
                .location(location)
                .orderDate(dto.getOrderDate())
                .expectedDate(dto.getExpectedDate())
                .status(POStatus.DRAFT)
                .build();

        List<POItem> poItems = new ArrayList<>();
        if (dto.getItems() != null && !dto.getItems().isEmpty()) {
            for (POItemDTO itemDto : dto.getItems()) {
                if (itemDto.getOrderedQty() == null || itemDto.getOrderedQty() <= 0) {
                    throw new ValidationException("Ordered quantity must be > 0");
                }
                if (itemDto.getUnitPrice() == null || itemDto.getUnitPrice() < 0) {
                    throw new ValidationException("Unit price must be >= 0");
                }

                Item item = itemRepository.findById(itemDto.getItemId())
                        .orElseThrow(() -> new ResourceNotFoundException("Item not found"));

                POItem poItem = POItem.builder()
                        .purchaseOrder(po)
                        .item(item)
                        .orderedQty(itemDto.getOrderedQty())
                        .unitPrice(itemDto.getUnitPrice())
                        .taxPct(itemDto.getTaxPct())
                        .build();
                poItems.add(poItem);
            }
        }

        po.setItems(poItems);
        PurchaseOrder savedPO = poRepository.save(po);
        return mapToDTO(savedPO);
    }

    @Override
    public PurchaseOrderDTO getPOById(Long id) {
        PurchaseOrder po = poRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase Order not found"));
        return mapToDTO(po);
    }

    @Override
    public PurchaseOrderDTO approvePO(Long id) {
        PurchaseOrder po = poRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase Order not found"));

        if (po.getStatus() == POStatus.CLOSED || po.getStatus() == POStatus.CANCELLED) {
            throw new ValidationException("Cannot modify a CLOSED or CANCELLED PO");
        }
        if (po.getStatus() != POStatus.DRAFT) {
            throw new ValidationException("Only DRAFT POs can be APPROVED");
        }

        po.setStatus(POStatus.APPROVED);
        return mapToDTO(poRepository.save(po));
    }

    @Override
    public PurchaseOrderDTO updateStatus(Long id, POStatus newStatus) {
        PurchaseOrder po = poRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase Order not found"));

        if (po.getStatus() == POStatus.CLOSED || po.getStatus() == POStatus.CANCELLED) {
            throw new ValidationException("Cannot modify a CLOSED or CANCELLED PO");
        }

        po.setStatus(newStatus);
        return mapToDTO(poRepository.save(po));
    }

    private PurchaseOrderDTO mapToDTO(PurchaseOrder po) {
        List<POItemDTO> itemDTOs = po.getItems().stream().map(item -> POItemDTO.builder()
                .poItemId(item.getPoItemId())
                .itemId(item.getItem().getItemId())
                .orderedQty(item.getOrderedQty())
                .unitPrice(item.getUnitPrice())
                .taxPct(item.getTaxPct())
                .build()).collect(Collectors.toList());

        return PurchaseOrderDTO.builder()
                .poId(po.getPoId())
                .vendorId(po.getVendor().getVendorId())
                .locationId(po.getLocation().getLocationId())
                .orderDate(po.getOrderDate())
                .expectedDate(po.getExpectedDate())
                .status(po.getStatus())
                .items(itemDTOs)
                .build();
    }
}
