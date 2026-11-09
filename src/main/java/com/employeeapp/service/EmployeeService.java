package com.employeeapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.employeeapp.dto.request.EmployeeRequestDTO;
import com.employeeapp.dto.response.EmployeeResponseDTO;
import com.employeeapp.entity.Employee;
import com.employeeapp.exceptions.EmployeeNotFoundException;
import com.employeeapp.repository.EmployeeRepository;

@Service
public class EmployeeService {
	private final EmployeeRepository repo;
	
	public EmployeeService(EmployeeRepository repo) {
		this.repo = repo;
	}
	
	
	public Employee toEntity(EmployeeRequestDTO dto){
		Employee emp=new Employee();
		emp.setName(dto.getName());
		emp.setEmail(dto.getEmail());
		emp.setSalary(dto.getSalary());
		emp.setDepartment(dto.getDepartment());
		return emp;
	}
	
	public List<Employee> toListEntity(List<EmployeeRequestDTO> dtos){
		List<Employee> emps=dtos.stream().map(this::toEntity).collect(Collectors.toList());
		return emps;
	}
	
	public EmployeeResponseDTO toResponseDto(Employee emp){
		EmployeeResponseDTO dto=new EmployeeResponseDTO();
		dto.setName(emp.getName());
		dto.setEmail(emp.getEmail());
		dto.setSalary(emp.getSalary());
		dto.setDepartment(emp.getDepartment());
		return dto;
	}
	
	public List<EmployeeResponseDTO> toListResponseDto(List<Employee> emps){
		List<EmployeeResponseDTO> dtos=emps.stream().map(this::toResponseDto).collect(Collectors.toList());
		return dtos;
	}

	
	public void saveEmployee(EmployeeRequestDTO emp) {
		repo.save(toEntity(emp));
	}
	
	public void saveAllEmployee(List<EmployeeRequestDTO> emps){
		repo.saveAll(toListEntity(emps));
	}
	
	public EmployeeResponseDTO getEmployeeById(Integer id) {
		Employee emp= repo.findById(id).orElseThrow(()->new EmployeeNotFoundException("Employee not found!"));
		if(emp.isDeleted()) {
			throw new EmployeeNotFoundException("Employee not found!");
		}
		return toResponseDto(emp);
	}
	
	public List<EmployeeResponseDTO> getAllEmployee(){
		return toListResponseDto(repo.findByIsDeletedFalse());
	}
	
	public void deleteEmployeeById(Integer id) {
		Employee exist=repo.findById(id).orElseThrow(()->new EmployeeNotFoundException("Employee not exists!"));
		if(exist.isDeleted()) {
			throw new EmployeeNotFoundException("Employee not exists!");
		}
		exist.setDeleted(true);
		repo.save(exist);
	}
	
	public void updateEmployee(Integer id,EmployeeRequestDTO emp) {
		Employee exist=repo.findById(id).orElseThrow(()->new EmployeeNotFoundException("Employee not exists"));
		if(exist.isDeleted()) {
			throw new EmployeeNotFoundException("Employee not exists!");
		}
		exist.setName(emp.getName());
		exist.setEmail(emp.getEmail());
		exist.setDepartment(emp.getDepartment());
		exist.setSalary(emp.getSalary());
		repo.save(exist);
	}
	
	public List<EmployeeResponseDTO> getByDepartment(String dept){
		return toListResponseDto(repo.findByDepartmentAndIsDeletedFalse(dept));
	}
}
