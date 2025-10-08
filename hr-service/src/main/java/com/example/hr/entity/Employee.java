package com.example.hr.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String position;
    private Double salary;

    public Employee(Long id) {
        this.id = id;
    }
}
