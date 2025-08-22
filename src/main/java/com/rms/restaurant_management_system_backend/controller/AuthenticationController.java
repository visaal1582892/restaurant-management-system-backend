package com.rms.restaurant_management_system_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.restaurant_management_system_backend.domain.AuthResponse;
import com.rms.restaurant_management_system_backend.domain.LoginRequest;
import com.rms.restaurant_management_system_backend.utilities.CustomResponse;
import com.rms.restaurant_management_system_backend.utilities.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<CustomResponse> login(@RequestBody LoginRequest request) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		String role = authentication.getAuthorities().iterator().next().getAuthority();
		String token = jwtUtil.generateToken(request.getUsername(), role);
		CustomResponse response = new CustomResponse(true, "Log in succussfull ", new AuthResponse(token, role));
		return ResponseEntity.ok(response);
	}

	@PostMapping("/logout")
	public ResponseEntity<CustomResponse> logout() {

		SecurityContextHolder.clearContext();
		CustomResponse response = new CustomResponse(true, "Logged out successfully!", null);
		return ResponseEntity.ok(response);
	}
}
