package com.example.loginjwt.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.loginjwt.config.JwtService;
import com.example.loginjwt.user.Role;
import com.example.loginjwt.user.User;
import com.example.loginjwt.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private final UserRepository repository;
	
	private final JwtService jwtService;
	
	public Map<String, Object> register(RegisterRequest request) {
		User user = User.builder()
				.firstname(request.getFirstname())
				.lastname(request.getLastname())
				.email(request.getEmail())
				.password(request.getPassword())
				.role(Role.USER)
				.build();
		repository.save(user);
		String jwtToken = jwtService.generateToken(user);
		System.out.println(jwtToken);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", true);
		result.put("message", "email berhasil terdaftar");
		return result;
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
		
		User user = repository.findByEmailAndPassword(request.getEmail(), request.getPassword()).orElseThrow(() -> new Exception());//

		System.out.println("user: " + user.getEmail());
		String jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}
	
}
