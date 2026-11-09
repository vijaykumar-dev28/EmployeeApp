package com.employeeapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.employeeapp.entity.User;
import com.employeeapp.enums.Role;
import com.employeeapp.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner{
	private final UserRepository repo;
	private final PasswordEncoder passwordEncoder;
	public DataInitializer(UserRepository repo, PasswordEncoder passwordEncoder) {
		this.repo = repo;
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	public void run(String... args) throws Exception {

		if(!repo.existsByUsername("admin")) {
			User user=new User();
			user.setUsername("admin");
			user.setPassword(passwordEncoder.encode("admin123"));
			user.setRole(Role.ADMIN);
			repo.save(user);
		}
	}

}
	