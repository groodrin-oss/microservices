package com.example.hr.controller;

import com.example.hr.entity.Employee;
import com.example.hr.repository.EmployeeRepository;
import com.example.hr.service.SyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final SyncService syncService;

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        Employee saved = employeeRepository.save(employee);
        syncService.sendSyncEvent("create", saved);
        return saved;
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        existing.setFirstName(employee.getFirstName());
        existing.setLastName(employee.getLastName());
        existing.setPosition(employee.getPosition());
        existing.setSalary(employee.getSalary());
        Employee saved = employeeRepository.save(existing);
        syncService.sendSyncEvent("update", saved);
        return saved;
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        syncService.sendSyncEvent("delete", new Employee(id));
    }
}
