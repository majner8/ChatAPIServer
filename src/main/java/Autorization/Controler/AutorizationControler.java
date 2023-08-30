package Autorization.Controler;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import Autorization.DTO.AutorizationRequestDTO;
import Autorization.DTO.TokenDTO;
import Autorization.Entity.AutorizationUserEntity;
import Autorization.JwtToken.JwtTokenInterface;
import Autorization.Service.AutorizationRepositoryInterface;
import User.Entity.UserEntity;
import User.Interface.UserRepositoryInterface;

public class AutorizationControler {

	@Autowired
	private BCryptPasswordEncoder BCryptEncoder;
	
	@Autowired
	private JwtTokenInterface tokenGenerator;
	@Autowired
	private UserRepositoryInterface UserService;
	@Autowired
	private AutorizationRepositoryInterface AutorizationService;
	
	
	/**Metod proces Registration task
	 * @return generated token if attemp will be sucesfull, token */
	@Transactional
	@PostMapping("/register")
	public ResponseEntity<TokenDTO>register(@RequestHeader String email,
			@RequestHeader String countryPreflix,@RequestHeader String phone,
		@RequestHeader String password){
		
		if(this.UserService.existsByEmailOrPhoneAndCountryPreflix(email, phone, countryPreflix)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}		
		UserEntity newEntity=new UserEntity();
		newEntity.setEmail(email);
		newEntity.setPhone(phone);
		newEntity.setCountry_preflix(countryPreflix);
		try {
		this.UserService.saveAndFlush(newEntity);
			AutorizationUserEntity autUser=new AutorizationUserEntity();
			autUser.setUserId(newEntity.getUserId());
			autUser.setPassword(this.BCryptEncoder.encode(password));
			this.AutorizationService.save(autUser);
		} catch(DataIntegrityViolationException ee) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return ResponseEntity.ok(this.tokenGenerator.generateToken(newEntity.getUserId(), false));
		
	}

	@PostMapping("/login")
	public ResponseEntity<TokenDTO>login(@RequestBody AutorizationRequestDTO autorization){
		
		
		Optional<UserEntity> users;
		if(email!=null) {
			users =this.UserService.findByEmail(email);
		}
		else {
			users=this.UserService.findByPhoneAndCountry_preflix(phone, deviceID);
		}
		if(users.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		UserEntity user=users.get();
		AutorizationUserEntity autUser=this.AutorizationService.findById(user.getUserId())
				.orElseThrow(()->{
					throw new RuntimeException("Chyba v datech, registrovaný uživatel nemá přidané heslo");
				});
		if(!this.BCryptEncoder.matches(password, autUser.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	
		return ResponseEntity.ok(this.tokenGenerator.generateToken(user.getUserId(), user.isUserActive()));
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
