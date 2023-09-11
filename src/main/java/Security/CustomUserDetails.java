package Security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class CustomUserDetails implements UserDetails{

	
	private Collection<? extends GrantedAuthority> autority;
	private int userId;
	private String StringUserID;
	private String deviceId;
	
	
	public String getDeviceId() {
		return deviceId;
	}

	/**Metod create new CustomUserDetails object
	 *  @return null if autority equal null, or userId is not an Integer */
	public static CustomUserDetails createCustomUserDetails(Collection<? extends GrantedAuthority> autority, String userId
			,String deviceId) {
		if(autority==null||userId==null) {
			return null;
		}
		try {
			return new CustomUserDetails(autority,Integer.parseInt(userId),deviceId);
		
		}
			catch(NumberFormatException e) {
				return null;
			}
	
		
	}
	
	protected CustomUserDetails(Collection<? extends GrantedAuthority> autority, int userId,String deviceId) {
		this.autority = autority;
		this.userId = userId;
		this.deviceId=deviceId;
		this.StringUserID=String.valueOf(this.userId);
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
		return this.StringUserID;
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

	public String getUserId() {
		return userId;
	}

	public String getStringUserId() {
		return this.StringUserID;
	}
	

	public static class AutorizationCustomUserDetails extends CustomUserDetails {

		private long DatabaseVersion;
		private AutorizationCustomUserDetails(long Version,Collection<? extends GrantedAuthority> autority, int userId,String deviceId) {
			super(autority, userId,deviceId);
			this.DatabaseVersion=Version;
			// TODO Auto-generated constructor stub
		}
		
		public static AutorizationCustomUserDetails createAutorizationCustomUserDetails(Long version,Collection<? extends GrantedAuthority> autority, String userId,String deviceId) {
			
			System.out.println(autority);
			System.out.println(userId);
			System.out.println(version);

			if(autority==null||userId==null||version==null) {
				return null;
			}
			try {
			return new AutorizationCustomUserDetails(version,autority,Integer.parseInt(userId),deviceId);
		
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
