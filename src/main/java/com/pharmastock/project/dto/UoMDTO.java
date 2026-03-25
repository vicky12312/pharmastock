package com.pharmastock.project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UoMDTO {

    private Long uomId;

    @NotBlank(message = "Code is required")
    private String code;

    private String description;
}
