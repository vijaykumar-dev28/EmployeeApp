package com.employeeapp.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
	private final JwtFilter jwtFilter;
	private final CustomUserDetailsService userService;
	public SecurityConfig(JwtFilter jwtFilter, CustomUserDetailsService userService) {
		this.jwtFilter = jwtFilter;
		this.userService = userService;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.cors(cors->cors.configurationSource(corsConfigurationSource()))
				.csrf(csrf->csrf.disable())
				.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth->auth
						.requestMatchers("/auth/login").permitAll()
						.requestMatchers("/auth/register-admin").hasRole("ADMIN")
						.requestMatchers("/employee/**").hasRole("ADMIN")
						.requestMatchers("/department/**").hasRole("ADMIN")
						.anyRequest().authenticated())
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEnocder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider(userService);
		provider.setPasswordEncoder(passwordEnocder());
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public  CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config=new CorsConfiguration();
		config.setAllowedHeaders(List.of("*"));
		config.setAllowedOrigins(List.of("http://localhost:5173"));
		config.setAllowedMethods(List.of("GET","POST","DELETE","PUT"));
		config.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
}
