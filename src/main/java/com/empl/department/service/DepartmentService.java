package com.empl.department.service;

import com.empl.department.payload.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentById(Long id);

    List<DepartmentDto> getAllDepartment(int pageNo, int pageSize, String sortBy, String sortDir);
}
