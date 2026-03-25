package com.pharmastock.project.service.impl;

import com.pharmastock.project.dto.ReplenishmentRuleDTO;
import com.pharmastock.project.entity.Item;
import com.pharmastock.project.entity.ReplenishmentRule;
import com.pharmastock.project.repository.ItemRepository;
import com.pharmastock.project.repository.ReplenishmentRuleRepository;
import com.pharmastock.project.service.ReplenishmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplenishmentServiceImpl implements ReplenishmentService {

    private final ReplenishmentRuleRepository ruleRepository;
    private final ItemRepository itemRepository;

    @Override
    public ReplenishmentRuleDTO createRule(ReplenishmentRuleDTO dto) {
        Item item = itemRepository.findById(dto.getItemId()).orElseThrow();
        ReplenishmentRule rule = ReplenishmentRule.builder()
                .locationId(dto.getLocationId())
                .item(item)
                .minLevel(dto.getMinLevel())
                .maxLevel(dto.getMaxLevel())
                .parLevel(dto.getParLevel())
                .reviewCycle(dto.getReviewCycle())
                .build();
        ReplenishmentRule saved = ruleRepository.save(rule);
        
        return ReplenishmentRuleDTO.builder()
                .ruleId(saved.getRuleId())
                .locationId(saved.getLocationId())
                .itemId(saved.getItem().getItemId())
                .minLevel(saved.getMinLevel())
                .maxLevel(saved.getMaxLevel())
                .parLevel(saved.getParLevel())
                .reviewCycle(saved.getReviewCycle())
                .build();
    }

    @Override
    public void generateSuggestions() {
        // Implementation stub for checking min levels and generating requests
    }
}
