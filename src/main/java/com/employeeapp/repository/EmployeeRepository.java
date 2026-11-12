package com.employeeapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeeapp.entity.Employee;
import com.employeeapp.enums.Department;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	Employee findByName(String name);
	
	List<Employee> findByDepartment(Department dept);
	
	List<Employee> findByIsDeletedFalse();
	
	List<Employee> findByDepartmentAndIsDeletedFalse(Department dept);
	
	List<Employee> findByNameContainingIgnoreCaseAndIsDeletedFalse(String name);
}
