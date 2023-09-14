package Authorization.Controler;


import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import Config.PathConfig;
import DTO.Autorization.AutorizationRequestDTO;
import DTO.Autorization.TokenDTO;
import Entity.AutorizationEntity.DeviceUserEntity;
import Entity.AutorizationEntity.DeviceUserEntity.CompositePrimaryKey;
import Entity.UserEntity.UserDataSettingDTO;
import Entity.UserEntity.UserEntity;
import Entity.UserEntity.UserPasswordEntity;
import Repository.AutorizationRepository.AutorizationRepositoryInterface;
import Repository.User.UserRepositoryInterface;
import Security.CustomUserDetails;
import Security.CustomUserDetails.AutorizationCustomUserDetails;
import Security.SecurityConfiguration;
import Security.JWT.JwtTokenInterface;

@RequestMapping(PathConfig.autorizationPathPreflix)
@Component
public class AuthorizationControler {

	@Autowired
	private BCryptPasswordEncoder BCryptEncoder;
	
	@Autowired
	private JwtTokenInterface tokenGenerator;
	@Autowired
	private UserRepositoryInterface UserService;
	@Autowired
	private AutorizationRepositoryInterface UserPasswordDatabaseService;
	@PersistenceContext
	private EntityManager entityManager;
	
	/**Metod proces Registration task
	 * @return generated token if attemp will be sucesfull, token */
	@Transactional
	@PostMapping(PathConfig.registerPath)
	public ResponseEntity<TokenDTO>register(@RequestBody @Valid AutorizationRequestDTO value,
			HttpServletRequest request){
		
		if(this.UserService.existsByEmailOrPhoneAndCountryPreflix(value.getEmail(), value.getPhone(), value.getCountryPreflix())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}		
		UserEntity newEntity=new UserEntity();
		newEntity.setEmail(value.getEmail());
		newEntity.setPhone(value.getPhone());
		newEntity.setCountry_preflix(value.getCountryPreflix());
		newEntity.setUserActive(false);
		
		try {
			this.UserService.saveAndFlush(newEntity);

			UserPasswordEntity autUser=new UserPasswordEntity();
			autUser.setUserId(newEntity.getUserId());
			autUser.setPassword(this.BCryptEncoder.encode(value.getPassword()));
			this.UserPasswordDatabaseService.save(autUser);
			
		} catch(DataIntegrityViolationException ee) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(this.tokenGenerator.generateToken(newEntity,value.getDeviceID()));
		
	}
	@Transactional
	@PostMapping(PathConfig.loginPath)
	public ResponseEntity<TokenDTO>login(@RequestBody @Valid AutorizationRequestDTO value
			,HttpServletRequest request){
		
		Optional<UserEntity> users;
		if(value.getEmail()!=null) {
			users =this.UserService.findByEmail(value.getEmail());
		}
		else {
			users=this.UserService.findByPhoneAndCountryPreflix(value.getPhone(), value.getDeviceID());
		}
		
		if(users.isEmpty()) {
			
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		UserEntity user=users.get();
		UserPasswordEntity autUser = this.UserPasswordDatabaseService.findById(user.getUserId())
                .orElseThrow(() -> {
                    throw new RuntimeException("Error in data, registered user does not have an added password");
                });

	
		if(!this.BCryptEncoder.matches(value.getPassword(), autUser.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return ResponseEntity.ok(this.tokenGenerator.generateToken(user,value.getDeviceID()));
	}
	
	
	/**Metod is processing finishRegistration request
	 * @return HttpStatuc 409, if registration has been finished from different device */
	@PostMapping(PathConfig.finisRegistrationPath)
	public ResponseEntity<TokenDTO>finishRegistration(@RequestBody UserDataSettingDTO value,
			@AuthenticationPrincipal AutorizationCustomUserDetails userDetails){
		
		UserEntity user=this.UserService.findById(userDetails.getUserId()).orElseThrow(()->{
			throw new UserWasNotFindException(userDetails.getUserId());
		});
		

		user.setSerName(value.getSerName());
		user.setLastName(value.getLastName());
		user.setBirthDay(value.getBirthDay());
		user.setUserActive(true);
		user.setVersion(userDetails.getDatabaseVersion());
		
		
		try {
			this.UserService.save(user);
		}
		catch(OptimisticLockException e) {
			//registration has been finish from other device
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.ok(this.tokenGenerator.generateToken(user.getUserId(),userDetails.getDeviceId()));
	}
	

	
	private static final class UserWasNotFindException extends RuntimeException{
		private UserWasNotFindException(int userId) {
			super(String.format("user %d was not find, even if it has done autorization before",userId ));
		}
	}
	
}
