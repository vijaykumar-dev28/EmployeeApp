package com.sprigboot.EmployeeApp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message="Name can't be empty")
	private String name;
	
	@Email(message="Enter the valid email")
	@NotBlank(message="Email can't be empty")
	private String email;
	
	@NotBlank(message="Department be can't be empty")
	private String department;
	
	@NotNull(message="Salary can't be null")
	@Min(value=1,message="Salary must be be more than 0!")
	private Double salary;
	
}

