package com.empl.department.service.impl;

import com.empl.department.entity.Department;
import com.empl.department.exception.ResourceNotFoundException;
import com.empl.department.payload.DepartmentDto;
import com.empl.department.repository.DepartmentRepository;
import com.empl.department.service.DepartmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {

        Department department = new Department();
        department.setName(departmentDto.getName());

        Department saveDepartment = departmentRepository.save(department);
        DepartmentDto dto = mapToDto(saveDepartment);
        return dto;
    }

    @Override
    public DepartmentDto getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Department not found with id:" + id)
        );
        DepartmentDto dto = mapToDto(department);
        return dto;
    }

    @Override
    public List<DepartmentDto> getAllDepartment(int pageNo, int pageSize, String sortBy, String sortDir) {

         Sort sort=(sortDir.equalsIgnoreCase("desc")) ? Sort.by(sortBy).descending():Sort.by(sortBy).ascending();


        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Department> pageDepartments = departmentRepository.findAll(pageable);
        List<Department> departments = pageDepartments.getContent();
        //convert list of dtos to entity using stream api
        List<DepartmentDto> dtos = departments.stream().map(d -> mapToDto(d)).collect(Collectors.toList());
        return dtos;
}

    DepartmentDto mapToDto(Department department){
        DepartmentDto dto=new DepartmentDto();
        dto.setId(department.getId());
        dto.setName(department.getName());
        return dto;
    }
}
