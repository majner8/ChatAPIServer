package Security;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.auth0.jwt.interfaces.Claim;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Security.JWT.JwtUtils;
import User.Entity.UserDevicesEntity;
import User.Entity.UserEntity;

public class CustomUserDetails implements UserDetails{

	
	private Collection<? extends GrantedAuthority> autority;
	private int userId;
	
	
	
	/**Metod create new CustomUserDetails object
	 *  @return null if autority equal null, or userId is not an Integer */
	public static CustomUserDetails createCustomUserDetails(Collection<? extends GrantedAuthority> autority, String userId) {
		if(autority==null||userId==null) {
			return null;
		}
		try {
			return new CustomUserDetails(autority,Integer.parseInt(userId));
		
		}
			catch(NumberFormatException e) {
				return null;
			}
	
		
	}
	
	protected CustomUserDetails(Collection<? extends GrantedAuthority> autority, int userId) {
		this.autority = autority;
		this.userId = userId;

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
		return String.valueOf(this.userId);
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
		return true;
	}

	public Collection<? extends GrantedAuthority> getAutority() {
		return autority;
	}

	public int getUserId() {
		return userId;
	}


	public static class AutorizationCustomUserDetails extends CustomUserDetails {

		private long DatabaseVersion;
		private AutorizationCustomUserDetails(long Version,Collection<? extends GrantedAuthority> autority, int userId) {
			super(autority, userId);
			this.DatabaseVersion=Version;
			// TODO Auto-generated constructor stub
		}
		
		public static AutorizationCustomUserDetails createAutorizationCustomUserDetails(Long version,Collection<? extends GrantedAuthority> autority, String userId) {
			if(autority==null||userId==null||version==null) {
				return null;
			}
			try {
			return new AutorizationCustomUserDetails(version,autority,Integer.parseInt(userId));
		
			}
			catch(NumberFormatException e) {
				return null;
			}
		}
		
		public long getDatabaseVersion() {
			return this.DatabaseVersion;
		}

		
	}
	

}
