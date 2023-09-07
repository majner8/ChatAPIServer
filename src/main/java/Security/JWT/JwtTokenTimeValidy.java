package Security.JWT;

/**
 * Interface for managing token validation.
 * - Provides methods to cancel or verify token validation.
 * - Canceling validation is managed by the database.
 */
public interface JwtTokenTimeValidy {
	 /**
     * Verify if the token is still valid.
     * 
     * @param tokenID the ID of the token to check.
     * @return true if the token is still valid, false otherwise.
     */
  public boolean isTokenStillValid(TokenID tokenID);
	
    /**
     * Cancel the time validity of a token before its actual expiration.
     * @param tokenID the ID of the token to invalidate.
     */
    public void logout(TokenID tokenID);
	
    /**
     * Cancel the time validity of multiple tokens before their actual expiration.
     * @param tokenIDs an array of token IDs to invalidate.
     */
   public void logout(TokenID[] tokenIDs);
}
