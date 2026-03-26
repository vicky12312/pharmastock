package com.pharmastock.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmastock.project.dto.PutAwayTaskDTO;
import com.pharmastock.project.service.PutAwayService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/warehouse/putaway")
@RequiredArgsConstructor
@Slf4j
public class PutAwayController {

    private final PutAwayService putAwayService;

    @PutMapping("/{taskId}/complete")
    public ResponseEntity<PutAwayTaskDTO> completeTask(@PathVariable("taskId") Long taskId) {
        log.info("Completing PutAway Task ID: {}", taskId);
        return ResponseEntity.ok(putAwayService.completeTask(taskId));
    }
}
