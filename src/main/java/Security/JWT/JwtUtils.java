package Security.JWT;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import Autorization.DTO.TokenDTO;
import User.Entity.UserEntity;
import io.jsonwebtoken.UnsupportedJwtException;

public class JwtUtils implements JwtTokenInterface{

	
	public static ObjectMapper jwtMapper=new ObjectMapper();

	private long expiration;
	private long Autorization_expiration;
	
	private final String secret;
	@Override
	public TokenDTO generateToken(UserEntity user) {
		// TODO Auto-generated method stub
		Date validUntil=this.TokenValidity(user.isUserActive());
		String token;
		
		if(user.isUserActive()) {
			token=this.generateToken(user.getUserId(), user.isUserActive(), new HashMap<String,String>(),validUntil);
		}
		else {
			Map<String, String> claims = new HashMap<>();
			claims.put("version", String.valueOf(user.getVersion()));
			token=this.generateToken(user.getUserId(), user.isUserActive(), claims,validUntil);
		}
		
		TokenDTO tok=new TokenDTO();
		tok.setToken(token);
		tok.setUserActive(user.isUserActive());
		tok.setValidUntil(validUntil);
		return tok;
	}

	@Override
	public TokenDTO generateToken(int userId) {
		// TODO Auto-generated method stub
		Date validUntil=this.TokenValidity(true);

		String token =this.generateToken(userId, true, new HashMap<String,String>(),validUntil);
	

		TokenDTO tok=new TokenDTO();
		tok.setToken(token);
		tok.setUserActive(true);
		tok.setValidUntil(validUntil);
		return tok;
	}
	
	

	
	private DecodedJWT verifyToken(String token) {
	        JWT.require(Algorithm.HMAC512(secret))
              .build()
              .verify(token);
	        DecodedJWT jwt=JWT.decode(token);
	        String sub=jwt.getSubject();
	        String active=jwt.getClaim("active").asString();
	        if(sub==null||active==null) {
	        	throw new UnsupportedJwtException(null);
	        }
	        
	        
	        
	        return jwt;

	} 
		
	private  String generateToken(long userId,boolean isUserActive,Map<String,String> claimValue,Date validUntil) {
			

		JWTCreator.Builder jwtBuilder= JWT.create()
					.withSubject(String.valueOf(userId))
					.withClaim("active",String.valueOf(isUserActive) )
					.withIssuedAt(new Date())
					.withExpiresAt(validUntil);
		
		claimValue.forEach((X,Z)->{
			jwtBuilder.withClaim(X,Z);
		});
		return "Bearer " +jwtBuilder.sign(Algorithm.HMAC512(secret));
		}	
		
	private Date TokenValidity(boolean isuserActive) {
		return new Date(System.currentTimeMillis()+(isuserActive==true?this.expiration:this.Autorization_expiration));
		
	}
}
