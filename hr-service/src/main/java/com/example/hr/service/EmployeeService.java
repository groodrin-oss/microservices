package com.example.hr.service;

import com.example.hr.entity.Employee;
import com.example.hr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final SyncService syncService;

    public Employee createEmployee(Employee employee) {
        Employee saved = employeeRepository.save(employee);
        syncService.sendSyncEvent("create", saved);
        return saved;
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        existing.setFirstName(employee.getFirstName());
        existing.setLastName(employee.getLastName());
        existing.setPosition(employee.getPosition());
        existing.setSalary(employee.getSalary());
        Employee saved = employeeRepository.save(existing);
        syncService.sendSyncEvent("update", saved);
        return saved;
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        syncService.sendSyncEvent("delete", new Employee(id));
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }
}
