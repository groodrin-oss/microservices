package com.example.payroll.model;

import com.example.payroll.entity.Employee;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SyncEvent {
    private String event;
    private Employee data;
}
