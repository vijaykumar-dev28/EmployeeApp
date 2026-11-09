package com.employeeapp.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employeeapp.dto.request.AuthRequestDTO;
import com.employeeapp.dto.response.AuthResponseDTO;
import com.employeeapp.entity.User;
import com.employeeapp.enums.Role;
import com.employeeapp.exceptions.DuplicateUserException;
import com.employeeapp.exceptions.UserNotFoundException;
import com.employeeapp.repository.UserRepository;
import com.employeeapp.security.JwtUtil;

@Service
public class AuthService {
	private final UserRepository userRepo;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager manager;
	private final JwtUtil jwtUtil;
	public AuthService(UserRepository userRepo, PasswordEncoder passwordEncoder, AuthenticationManager manager,
			JwtUtil jwtUtil) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
		this.manager = manager;
		this.jwtUtil = jwtUtil;
	}
	
	
	
	public void registerAdmin(AuthRequestDTO dto) {
		if(userRepo.existsByUsername(dto.getUsername())) {
			throw new DuplicateUserException("username already exists");
		}
		User user =new User();
		user.setUsername(dto.getUsername());
		user.setRole(Role.ADMIN);
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		userRepo.save(user);
	}
	
	public AuthResponseDTO login(AuthRequestDTO dto) {
		manager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword()));
		String token=jwtUtil.generateToken(dto.getUsername());
		User user=userRepo.findByUsername(dto.getUsername()).orElseThrow(()->new UserNotFoundException("Username not exisrs!"));
		return new AuthResponseDTO(token, dto.getUsername(),user.getRole());
	}
}
