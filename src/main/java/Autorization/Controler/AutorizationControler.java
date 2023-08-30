package Autorization.Controler;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import Autorization.DTO.TokenDTO;
import Autorization.Service.AutorizationRepositoryInterface;
import Entity.AutorizationUserEntity;

public class AutorizationControler {

	@Autowired
	private AutorizationRepositoryInterface AutorizationService;
	
	@PostMapping("/register")
	public ResponseEntity<String>register(@RequestHeader String email,
			@RequestHeader String countryPreflix,@RequestHeader String phone,
		@RequestHeader String password	){
		
		if(this.AutorizationService.existsByEmailOrPhoneAndCountryPreflix(email, phone, countryPreflix)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}		
		AutorizationUserEntity newEntity=new AutorizationUserEntity();
		newEntity.setEmail(email);
		newEntity.setPhone(phone);
		newEntity.setCountry_preflix(countryPreflix);
		newEntity.setPassword(password);
		try {
		this.AutorizationService.saveAndFlush(newEntity);
		} catch(DataIntegrityViolationException ee) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
		
		
	}

	@PostMapping("/login")
	public ResponseEntity<TokenDTO>login(@RequestHeader String email,
			@RequestHeader String countryPreflix,@RequestHeader String phone,
		@RequestHeader String password,@RequestHeader String deviceID){
		
		return null;
	}
	@PostMapping("/finishRegistration")
	public ResponseEntity<TokenDTO>finishRegistration(@RequestHeader String surName,
		@RequestHeader String lastName, @RequestHeader String bornDate,
		@AuthenticationPrincipal UserDetails authentication){

		AutorizationUser user=new AutorizationUser();
		
		user.setUserId(Integer.parseInt(authentication.getUsername()));
		
		
		
		return null;
	}
	

}
