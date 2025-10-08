package com.example.payroll.service;

import com.example.payroll.entity.Employee;
import com.example.payroll.model.SyncEvent;
import com.example.payroll.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeSyncService {

    private final EmployeeRepository employeeRepository;

    @Transactional
    public void handleEvent(SyncEvent event) {
        if (event == null || event.getEvent() == null) return;
        Employee emp = event.getData();

        switch (event.getEvent()) {
            case "create":
            case "update":
                if (emp == null || emp.getId() == null) {
                    throw new IllegalArgumentException("Invalid payload: data.id is required for create/update");
                }
                employeeRepository.save(emp);
                break;

            case "delete":
                if (emp != null && emp.getId() != null) {
                    employeeRepository.findById(emp.getId()).ifPresent(employeeRepository::delete);
                }
                break;

            default:
                break;
        }
    }
}
