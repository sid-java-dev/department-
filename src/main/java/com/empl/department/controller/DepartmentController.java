package com.empl.department.controller;

import com.empl.department.payload.DepartmentDto;
import com.empl.department.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto dto = departmentService.createDepartment(departmentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/particular")
    public ResponseEntity<DepartmentDto> getDepartment(@RequestParam long id) {
        DepartmentDto dto = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
//http://localhost:8080/api/department?pageNo=0&pageSize=5&sortBy=name&sortDir=desc
    @GetMapping
    public List<DepartmentDto> getAllDepartment(
            @RequestParam(name = "pageNo",required = false,defaultValue = "0")int pageNo,
            @RequestParam(name = "pageSize",required = false,defaultValue = "3")int pageSize,
            @RequestParam(name = "sortBy",required = false,defaultValue = "id")String sortBy,
            @RequestParam(name = "sortDir",required = false,defaultValue = "asc")String sortDir
    ){
        List<DepartmentDto>departmentDtos=departmentService.getAllDepartment(pageNo,pageSize,sortBy,sortDir);
        return departmentDtos;
    }

}
