package com.employeeapp.dto.response;

import com.employeeapp.enums.Department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDTO {
	private Integer id;
	private String name;
	private String email;
	private Department department;
	private Double salary;
}
