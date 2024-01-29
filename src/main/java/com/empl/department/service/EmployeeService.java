package com.empl.department.service;

import com.empl.department.payload.EmployeeDto;
import com.empl.department.repository.DepartmentRepository;

public interface EmployeeService {



     EmployeeDto  createEmployee(EmployeeDto employeeDto, long departmentId);


}
