package com.globel.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Setter
@Getter
public class JWTAuthenticatedResponse {

	
	private String token ;
	
	private String refreshToken;
	
}
