package com.rms.restaurant_management_system_backend.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.rms.restaurant_management_system_backend.service.implementation.CustomUserDetailsServiceImpl;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	CustomUserDetailsServiceImpl userDetailsService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors(cors -> cors.configurationSource(corsConfigurationSource())).csrf().disable().authorizeHttpRequests()
				.requestMatchers("/login", "/signup").permitAll().anyRequest().permitAll().and().formLogin()
				.loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password")
				.successHandler((req, res, auth) -> {
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

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("*")); // Allow all headers
		configuration.setAllowCredentials(true); // Allow sending credentials (cookies, auth headers)

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/", configuration); // Apply to all paths
		return source;
	}
}