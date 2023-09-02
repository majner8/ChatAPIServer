package Security.JWT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import Autorization.Controler.AutorizationControler;
import Autorization.Controler.CustomUserDetails;
import Security.SecurityConfigEnum;
import Security.SecurityConfiguration;

public class JwtTokenFilter extends OncePerRequestFilter{

	@Autowired
	private JwtTokenInterface tokenValidation;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(request.getRequestURI().equals("/autorization"+AutorizationControler.loginPath)
			||request.getRequestURI().equals("/autorization"+AutorizationControler.registerPath)	) {
			filterChain.doFilter(request, response);
			return;
		}
		String header=request.getHeader(HttpHeaders.AUTHORIZATION); 
		 if(header==null||header.isEmpty()||!header.startsWith(SecurityConfigEnum.jwtTokenPreflix.toString())) {
			 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			 return;
		 }	
	
		DecodedJWT jwt=	this.tokenValidation.tokenValidation(request);
	
		List <GrantedAuthority> autority=new ArrayList<GrantedAuthority>();
		Claim act=jwt.getClaim(SecurityConfigEnum.UserIsActiveClaimName.toString());
		CustomUserDetails user;
		if(Boolean.valueOf(act.asString())==true) {
			autority.add(new SimpleGrantedAuthority(SecurityConfiguration.userIsActiveRole));
		}
		else {
			
		}
		
		
	 CustomUserDetails user=new CustomUserDetails()
	 SecurityContextHolder.getContext().setAuthentication(null)
	
	}

	
}
