package com.collaborativeworkspace.user_service.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.collaborativeworkspace.user_service.dto.LoginRequest;
import com.collaborativeworkspace.user_service.dto.RegisterRequest;
import com.collaborativeworkspace.user_service.entity.User;
import com.collaborativeworkspace.user_service.repository.UserRepository;

@Service
public class AuthService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	
	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}
	
	public String registerUser(RegisterRequest registerRequest) {
		if(!userRepository.findByUsername(registerRequest.getUsername()).isEmpty()) {
			throw new RuntimeException("Username already exists!");
		}
		
		User newUser = new User();
		newUser.setUsername(registerRequest.getUsername());
		newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		
		userRepository.save(newUser);
		
		return "User registered successfully";
		
	}
	
	public String loginUser(LoginRequest loginRequest) {
		User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new RuntimeException("Invalid username or password!"));
		
		if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid username or password!");
		}
		
		return jwtService.generateToken(user.getUsername());
	}
}
