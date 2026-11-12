package com.employeeapp.dto.request;

import com.employeeapp.enums.Department;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDTO {
	@NotBlank(message = "Name can't be empty")
	private String name;
	@NotBlank(message = "Email can't be empty")
	@Email(message = "Invalid email")
	private String email;
	@NotNull(message = "Department can't be empty")
	private Department department;
	@NotNull(message = "Salary can't be empty")
	@Min(value = 1,message = "Salary must be greater than 0!")
	private Double salary;
}
