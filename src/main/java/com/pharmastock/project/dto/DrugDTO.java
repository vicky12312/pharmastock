package com.pharmastock.project.dto;

import com.pharmastock.project.entity.enums.ControlClass;
import com.pharmastock.project.entity.enums.Form;
import com.pharmastock.project.entity.enums.Status;
import com.pharmastock.project.entity.enums.StorageClass;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrugDTO {

    private Long drugId;

    @NotBlank(message = "Generic name is required")
    private String genericName;

    private String brandName;

    private String strength;

    @NotNull(message = "Form is required")
    private Form form;

    private String atcCode;

    @NotNull(message = "Control class is required")
    private ControlClass controlClass;

    @NotNull(message = "Storage class is required")
    private StorageClass storageClass;

    @NotNull(message = "Status is required")
    private Status status;
}
