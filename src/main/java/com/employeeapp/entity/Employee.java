package com.employeeapp.entity;

import com.employeeapp.enums.Department;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private Integer id;
	@Column(name = "employee_name",nullable = false)
	private String name;
	@Column(nullable = false,unique = true)
	private String email;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Department department;
	@Column(nullable = false)
	private Double salary;
	@Column(nullable = false)
	private boolean isDeleted=false;
	
}

