package Autorization.JwtToken;

import Autorization.DTO.TokenDTO;

public interface JwtTokenInterface {

	public TokenDTO generateToken(int userId,boolean isUserActive);
	
}
