package com.employeeapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeapp.dto.request.AuthRequestDTO;
import com.employeeapp.dto.response.AuthResponseDTO;
import com.employeeapp.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthService service;
	public AuthController(AuthService service) {
		this.service = service;
	}


	@PostMapping("/register-admin")
	public ResponseEntity<String> registerAdmin(@Valid @RequestBody AuthRequestDTO dto) {
		service.registerAdmin(dto);
		return ResponseEntity.status(201).body("Admin added successfully");
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthRequestDTO dto) {
		return ResponseEntity.ok(service.login(dto));
	}
}
