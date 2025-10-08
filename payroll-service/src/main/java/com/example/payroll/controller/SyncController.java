package com.example.payroll.controller;

import com.example.payroll.model.SyncEvent;
import com.example.payroll.service.EmployeeSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sync")
@RequiredArgsConstructor
public class SyncController {

    private final EmployeeSyncService employeeSyncService;

    @PostMapping("/employees")
    public ResponseEntity<Void> syncEmployee(@RequestBody SyncEvent event) {
        employeeSyncService.handleEvent(event);
        return ResponseEntity.ok().build();
    }
}
