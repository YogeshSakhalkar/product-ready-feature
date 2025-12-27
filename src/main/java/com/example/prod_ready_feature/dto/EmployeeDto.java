package com.example.prod_ready_feature.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class EmployeeDto {
    private Long id;

    private String name;

    private  Integer age;

    private String email;

    private Boolean isActive;

    private LocalDate dateOfJoining;

    private String role;


    private Double salary;

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                ", dateOfJoining=" + dateOfJoining +
                ", role='" + role + '\'' +
                ", salary=" + salary +
                '}';
    }
}
