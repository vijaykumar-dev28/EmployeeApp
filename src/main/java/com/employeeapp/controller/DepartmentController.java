package com.employeeapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeapp.enums.Department;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	@GetMapping
	public ResponseEntity<List<Department>> getAllDepartments(){
		return ResponseEntity.ok(List.of(Department.values()));
	}
}
