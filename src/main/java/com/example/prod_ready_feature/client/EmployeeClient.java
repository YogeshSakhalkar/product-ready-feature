package com.example.prod_ready_feature.client;

import com.example.prod_ready_feature.dto.EmployeeDto;

import java.util.List;

public interface EmployeeClient {

    List<EmployeeDto> getAllEmployee();

    EmployeeDto getEmployeeById(Long employeeId);

    EmployeeDto createNewEmployee(EmployeeDto employeeDto);

}
