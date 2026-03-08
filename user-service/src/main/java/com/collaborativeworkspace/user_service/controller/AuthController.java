package com.collaborativeworkspace.user_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.collaborativeworkspace.user_service.dto.LoginRequest;
import com.collaborativeworkspace.user_service.dto.RegisterRequest;
import com.collaborativeworkspace.user_service.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
		String result = authService.registerUser(registerRequest);
		
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
		String result = authService.loginUser(loginRequest);
		
		return ResponseEntity.ok(result);
	}
	
}
