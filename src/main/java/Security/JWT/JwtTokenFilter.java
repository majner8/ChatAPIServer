package Security.JWT;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import Autorization.Controler.AutorizationControler;

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
		
	
		DecodedJWT jwt=	this.tokenValidation.tokenValidation(request);
	
		Claim act=jwt.getClaim("active");
		if(Boolean.valueOf(act.asString())==true) {
			
		}
		else {
			
		}
		String header=request.getHeader(HttpHeaders.AUTHORIZATION);
	 
	 if(header==null||header.isEmpty()||!header.startsWith("Bearer ")) {
		 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		 return;
	 }	
	 
		this.tokenValidat
	}

	
}
