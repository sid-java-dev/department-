package com.empl.department.controller;

import com.empl.department.payload.EmployeeDto;
import com.empl.department.service.EmployeeService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDto>createEmployee(@RequestBody EmployeeDto employeeDto, @RequestParam long departmentId){
         EmployeeDto dto=employeeService.createEmployee(employeeDto,departmentId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
