package com.empl.department.service.impl;

import com.empl.department.entity.Department;
import com.empl.department.entity.Employee;
import com.empl.department.exception.ResourceNotFoundException;
import com.empl.department.payload.DepartmentDto;
import com.empl.department.payload.EmployeeDto;
import com.empl.department.repository.DepartmentRepository;
import com.empl.department.repository.EmployeeRepository;
import com.empl.department.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.sql.DriverManager;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private DepartmentRepository departmentRepository;
   private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto, long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));

        // Create Employee entity from EmployeeDto
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setDesignation(employeeDto.getDesignation());
        employee.setDepartment(department);
        Employee savedEmployee = employeeRepository.save(employee);


        EmployeeDto dto = mapToDto(savedEmployee);
        return dto;
    }
    EmployeeDto mapToDto(Employee  employee){
        EmployeeDto dto=new EmployeeDto();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setDesignation(employee.getDesignation());
        return dto;

    }
}
