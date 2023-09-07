package Security.JWT;

public class TokenID {

	private final String tokenID;
	private final String userID;
	private final String deviceID;
	
	
	public TokenID(String tokenID, String userID, String deviceID) {
		this.tokenID = tokenID;
		this.userID = userID;
		this.deviceID = deviceID;
	}
	public String getTokenID() {
		return tokenID;
	}
	public String getUserID() {
		return userID;
	}
	public String getDeviceID() {
		return deviceID;
	}
	
	
}
