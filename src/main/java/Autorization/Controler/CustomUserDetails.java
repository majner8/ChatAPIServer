package Autorization.Controler;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.auth0.jwt.interfaces.Claim;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Autorization.JwtToken.JwtUtils;
import User.Entity.UserDevicesEntity;
import User.Entity.UserEntity;

public class CustomUserDetails implements UserDetails{

	
	private Collection<? extends GrantedAuthority> autority;
	private int userId;
	private String phone;
	private String emailAdress;
	private Map<String,Claim> tokenClaim;
	private boolean finishRegistration;
	
	
	
	public UserEntity getUserEntityFromToken() {
		try {
			return JwtUtils.jwtMapper.readValue(this.tokenClaim.get("").asString(), UserEntity.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public UserDevicesEntity getDeviceUserEntity() {
		try {
			return JwtUtils.jwtMapper.readValue(this.tokenClaim.get("").asString(), UserDevicesEntity.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.autority;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.emailAdress==null?this.phone:this.emailAdress;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.finishRegistration;
	}

}
