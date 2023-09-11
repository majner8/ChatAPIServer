package Security.JWT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import Config.PathConfig;
import Security.CustomUserDetails;
import Security.SecurityConfiguration;

/**
 * This filter verifies if a request contains a JWT token and then creates an authentication.
 * The filter does not verify paths meant for authorization, specifically server login and registration requests.
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter{

	@Autowired
	private JwtTokenInterface tokenValidation;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
        // If the request URI is for authorization (login or registration), skip JWT verification
		if(request.getRequestURI().equals("/autorization"+PathConfig.loginPath)
			||request.getRequestURI().equals("/autorization"+PathConfig.registerPath)	) {
			filterChain.doFilter(request, response);
			return;
		}
		
		
		CustomUserDetails user;
		DecodedJWT jwt=	this.tokenValidation.tokenValidation(request);
		if(jwt==null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			 return;
		}
		
		List <GrantedAuthority> autority=new ArrayList<GrantedAuthority>();
		Claim act=jwt.getClaim(SecurityConfiguration.userIsActiveClaimName);
		//user is active
		if(Boolean.valueOf(act.asString())==true) {
			autority.add(new SimpleGrantedAuthority(SecurityConfiguration.userIsActiveRole));
			user=CustomUserDetails.createCustomUserDetails(autority, jwt.getClaim(SecurityConfiguration.userIdClaimName).asString(),jwt.getSubject());
			
		}
		//user is not active
		else {
			autority.add(new SimpleGrantedAuthority(SecurityConfiguration.userIsNotActiveRole));
			Long version;
			try {
			 version=Long.parseLong(this.removeQuotes(jwt.getClaim(SecurityConfiguration.VersionClaimName).asString()));
			 user=CustomUserDetails.AutorizationCustomUserDetails.createAutorizationCustomUserDetails(version, autority,  jwt.getClaim(SecurityConfiguration.userIdClaimName).asString(),jwt.getSubject());

			}
			catch(NumberFormatException e) {
				user=null;
			}
		}
		if(user==null) {
		 filterChain.doFilter(request, response);
		return; 
		}
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()); 
		SecurityContextHolder.getContext().setAuthentication(auth);
		filterChain.doFilter(request, response);
		
	}
	

	private static String removeQuotes(String claimValue) {
		if(claimValue.startsWith("\"")){
			claimValue=claimValue.substring(1);
		}
		if(claimValue.endsWith("\"")){
			claimValue=claimValue.substring(0,claimValue.length()-1);
		}
		return claimValue;
		}
}
