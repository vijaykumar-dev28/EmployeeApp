package com.sprigboot.EmployeeApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository repo;
	
	public Employee saveEmployee(Employee emp) {
		return repo.save(emp);
	}
	
	public List<Employee> saveAllEmployee(List<Employee> emp){
		return repo.saveAll(emp);
	}
	
	public Employee getEmployeeById(int id) {
		return repo.findById(id).orElse(null);
	}
	
	public List<Employee> getAllEmployee(){
		return repo.findAll();
	}
	
	public void deleteEmployeeById(int id) {
		repo.deleteById(id);
	}
	
	public Employee updateEmployee(int id,Employee emp) {
		Employee exist=repo.findById(id).orElse(null);
		if(exist!=null) {
			exist.setName(emp.getName());
			exist.setEmail(emp.getEmail());
			exist.setDepartment(emp.getDepartment());
			exist.setSalary(emp.getSalary());
			return repo.save(exist);
		}
		return null;
	}
	
	public List<Employee> getByDepartment(String dept){
		return repo.findByDepartment(dept);
	}
}
