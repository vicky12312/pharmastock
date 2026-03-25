package com.pharmastock.project.service;

import com.pharmastock.project.dto.PutAwayTaskDTO;

public interface PutAwayService {
    PutAwayTaskDTO completeTask(Long taskId);
}
