package Autorization.Controler;


import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import Autorization.DTO.TokenDTO;

public class AutorizationControler {


	@PostMapping("/register")
	public ResponseEntity<TokenDTO>register(@RequestHeader String email,
			@RequestHeader String countryPreflix,@RequestHeader String phone,
		@RequestHeader String password	){

		return null;
	}

	@PostMapping("/login")
	public ResponseEntity<TokenDTO>login(@RequestHeader String email,
			@RequestHeader String countryPreflix,@RequestHeader String phone,
		@RequestHeader String password,@RequestHeader String deviceID){
		
		return null;
	}
	@PostMapping("/finishRegistration/{userID}")
	public ResponseEntity<TokenDTO>finishRegistration(@PathVariable String userID,@RequestHeader String surName,
		@RequestHeader String lastName, @RequestHeader String bornDate){

		
		return null;
	}
	

}
