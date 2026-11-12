package com.employeeapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employeeapp.dto.request.EmployeeRequestDTO;
import com.employeeapp.dto.response.EmployeeResponseDTO;
import com.employeeapp.enums.Department;
import com.employeeapp.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	private final EmployeeService service;
	
	public EmployeeController(EmployeeService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<String> saveEmployee(@Valid @RequestBody EmployeeRequestDTO emp) {
		service.saveEmployee(emp);
		return ResponseEntity.status(201).body("Added Successfully!");
	}
	
	@PostMapping("saveAll")
	public ResponseEntity<String> saveAllEmployee(@Valid @RequestBody List<EmployeeRequestDTO> emp){
		service.saveAllEmployee(emp);
		return ResponseEntity.status(201).body("All employees are saved!");
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.getEmployeeById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployee(){
		return ResponseEntity.ok(service.getAllEmployee());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable Integer id) {
		service.deleteEmployeeById(id);
		return ResponseEntity.ok("Employee deleted");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable int id,@Valid @RequestBody EmployeeRequestDTO emp) {
		service.updateEmployee(id, emp);
		return ResponseEntity.ok("Employee updated successfully!");
	}
	
	@GetMapping("/dept/{dept}")
	public ResponseEntity<List<EmployeeResponseDTO>> getByDepartment(@PathVariable Department dept){
		return ResponseEntity.ok(service.getByDepartment(dept));
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<EmployeeResponseDTO>> searchByName(@RequestParam String name){
		return ResponseEntity.ok(service.searchByName(name));
	}
}
