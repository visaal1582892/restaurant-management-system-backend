package com.rms.restaurant_management_system_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.rms.restaurant_management_system_backend.service.implementation.CustomUserDetailsServiceImpl;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	CustomUserDetailsServiceImpl userDetailsService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests().requestMatchers("/login", "/signup").permitAll().anyRequest()
				.authenticated().and().formLogin().loginProcessingUrl("/login").usernameParameter("username")
				.passwordParameter("password").successHandler((req, res, auth) -> {
					System.out.println("good...................");
					res.setStatus(HttpServletResponse.SC_OK);
				}).failureHandler((req, res, exp) -> {
					System.out.println("bad...................");
					res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid credentials");
				}).and().sessionManagement().maximumSessions(1);
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
		return NoOpPasswordEncoder.getInstance();
	}
}
