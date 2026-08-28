package com.sprigboot.EmployeeApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService service;
	
	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee emp) {
		Employee saved=service.saveEmployee(emp);
		return ResponseEntity.status(201).body(saved);
	}
	
	@PostMapping("saveAll")
	public ResponseEntity<List<Employee>> saveAllEmployee(@RequestBody List<Employee> emp){
		List<Employee> saved=service.saveAllEmployee(emp);
		return ResponseEntity.status(201).body(saved);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
		Employee employee=service.getEmployeeById(id);
		if(employee!=null) {
			return ResponseEntity.ok(employee);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployee(){
		return ResponseEntity.ok(service.getAllEmployee());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable int id) {
		service.deleteEmployeeById(id);
		return ResponseEntity.ok("Employee deleted successfully!");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int id,@RequestBody Employee emp) {
		Employee updated=service.updateEmployee(id, emp);
		if(updated!=null) {
			return ResponseEntity.ok(updated);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/dept/{dept}")
	public ResponseEntity<List<Employee>> getByDepartment(@PathVariable String dept){
		List<Employee> emps=service.getByDepartment(dept);
		return ResponseEntity.ok(emps);
	}
}
