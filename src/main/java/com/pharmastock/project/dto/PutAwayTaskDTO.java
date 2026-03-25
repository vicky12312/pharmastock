package com.pharmastock.project.dto;

import com.pharmastock.project.entity.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PutAwayTaskDTO {

    private Long taskId;
    private Long grnItemId;
    private Long targetBinId;
    private Integer quantity;
    private TaskStatus status;
}
