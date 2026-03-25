package com.pharmastock.project.service.impl;

import com.pharmastock.project.dto.PutAwayTaskDTO;
import com.pharmastock.project.entity.PutAwayTask;
import com.pharmastock.project.entity.enums.TaskStatus;
import com.pharmastock.project.exception.ResourceNotFoundException;
import com.pharmastock.project.repository.PutAwayTaskRepository;
import com.pharmastock.project.service.PutAwayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PutAwayServiceImpl implements PutAwayService {

    private final PutAwayTaskRepository putAwayTaskRepository;

    @Override
    public PutAwayTaskDTO completeTask(Long taskId) {
        PutAwayTask task = putAwayTaskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Put Away Task not found"));

        task.setStatus(TaskStatus.COMPLETED);
        PutAwayTask saved = putAwayTaskRepository.save(task);

        return PutAwayTaskDTO.builder()
                .taskId(saved.getTaskId())
                .grnItemId(saved.getGrnItem().getGrnItemId())
                .targetBinId(saved.getTargetBin().getBinId())
                .quantity(saved.getQuantity())
                .status(saved.getStatus())
                .build();
    }
}
