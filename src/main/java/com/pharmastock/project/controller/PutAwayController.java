package com.pharmastock.project.controller;

import com.pharmastock.project.dto.PutAwayTaskDTO;
import com.pharmastock.project.service.PutAwayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/warehouse/putaway")
@RequiredArgsConstructor
public class PutAwayController {

    private final PutAwayService putAwayService;

    @PutMapping("/{taskId}/complete")
    public ResponseEntity<PutAwayTaskDTO> completeTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(putAwayService.completeTask(taskId));
    }
}
