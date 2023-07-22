package com.example.loginjwt.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
	
	private String firstname;
	private String lastname;
	private String email;
	private String password;

}
