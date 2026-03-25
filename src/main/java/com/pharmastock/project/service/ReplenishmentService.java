package com.pharmastock.project.service;
import com.pharmastock.project.dto.ReplenishmentRuleDTO;
public interface ReplenishmentService {
    ReplenishmentRuleDTO createRule(ReplenishmentRuleDTO dto);
    void generateSuggestions();
}
