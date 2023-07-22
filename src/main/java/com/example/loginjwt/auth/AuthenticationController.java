package com.example.loginjwt.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	private final AuthenticationService service;
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> register (@RequestBody RegisterRequest request){
		
		return ResponseEntity.ok(service.register(request));
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login (@RequestBody AuthenticationRequest request) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		AuthenticationResponse auth = new AuthenticationResponse();
		auth = service.authenticate(request);
		
		result.put("token", auth.getToken());
		result.put("message", "login berhasil");
		result.put("status", true);

		return ResponseEntity.ok(result);
		
	}
	
}
