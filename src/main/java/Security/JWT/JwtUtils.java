package Security.JWT;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import Autorization.DTO.TokenDTO;
import Security.SecurityConfiguration;
import User.Entity.UserEntity;
import io.jsonwebtoken.UnsupportedJwtException;
@Service
public class JwtUtils implements JwtTokenInterface{

	
	//public static ObjectMapper jwtMapper=new ObjectMapper();
	@Value("${jwt.expiration}")
	private long expiration;
	@Value("${jwt.authorizationExpiration}")
	private long authorizationExpiration;
	@Value("jwt.SecretKey")
	private String SecretKey;
	
	@Override
	public TokenDTO generateToken(UserEntity user,String deviceID) {
		// TODO Auto-generated method stub
		Date validUntil=this.TokenValidity(user.isUserActive());
		String token;
		
		if(user.isUserActive()) {
			token=this.generateToken(user.getUserId(), user.isUserActive(), new HashMap<String,String>(),validUntil,deviceID);
		}
		else {
			Map<String, String> claims = new HashMap<>();
			
			claims.put(SecurityConfiguration.VersionClaimName, String.valueOf(user.getVersion()));
			token=this.generateToken(user.getUserId(), user.isUserActive(), claims,validUntil,deviceID);
			
		}
		
		TokenDTO tok=new TokenDTO();
		tok.setToken(token);
		tok.setUserActive(user.isUserActive());
		tok.setValidUntil(validUntil);
		return tok;
	}

	@Override
	public TokenDTO generateToken(int userId,String deviceID) {
		// TODO Auto-generated method stub
		Date validUntil=this.TokenValidity(true);

		String token =this.generateToken(userId, true, new HashMap<String,String>(),validUntil,deviceID);
	

		TokenDTO tok=new TokenDTO();
		tok.setToken(token);
		tok.setUserActive(true);
		tok.setValidUntil(validUntil);
		return tok;
	}
	
	

	
	private DecodedJWT verifyToken(String token) {
	        JWT.require(Algorithm.HMAC512(this.SecretKey))
              .build()
              .verify(token);
	        DecodedJWT jwt=JWT.decode(token);
	        String sub=jwt.getSubject();
	        String active=jwt.getClaim(SecurityConfiguration.userIsActiveClaimName).asString();
	        String userID= jwt.getClaim(SecurityConfiguration.userIdClaimName).asString();
	        if(sub==null||active==null||userID==null) {
	        	throw new UnsupportedJwtException(null);
	        }
	        return jwt;

	} 
		
	private  String generateToken(long userId,boolean isUserActive,Map<String,String> claimValue,Date validUntil,String deviceId) {
			
	
		JWTCreator.Builder jwtBuilder= JWT.create()
					.withSubject(deviceId)
					.withClaim(SecurityConfiguration.userIsActiveClaimName,String.valueOf(isUserActive) )
					.withClaim(SecurityConfiguration.userIdClaimName, String.valueOf(userId))
					.withIssuedAt(new Date())
					.withExpiresAt(validUntil);
		
		claimValue.forEach((X,Z)->{
			jwtBuilder.withClaim(X,Z);
		});
		return "Bearer " +jwtBuilder.sign(Algorithm.HMAC512(this.SecretKey));
		}	
		
	private Date TokenValidity(boolean isuserActive) {
		return new Date(System.currentTimeMillis()+(isuserActive==true?this.expiration:this.authorizationExpiration));
		
	}

	@Override
	public DecodedJWT tokenValidation(HttpServletRequest request) {
		String header=request.getHeader(HttpHeaders.AUTHORIZATION); 
		
		if(header==null||header.isEmpty()||!header.startsWith(SecurityConfiguration.jwtTokenPreflix)) {
			 return null;
		 }	
		
		
		return this.verifyToken(header.replace(SecurityConfiguration.jwtTokenPreflix, ""));
	}
}
