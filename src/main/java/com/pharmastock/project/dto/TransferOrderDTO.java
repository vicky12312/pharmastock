package com.pharmastock.project.dto;

import com.pharmastock.project.entity.enums.TransferStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferOrderDTO {
    private Long toId;
    
    @NotNull(message = "Source location required")
    private Long fromLocationId;
    
    @NotNull(message = "Destination location required")
    private Long toLocationId;
    
    private LocalDateTime createdDate;
    private TransferStatus status;
    private List<TransferItemDTO> items;
}
