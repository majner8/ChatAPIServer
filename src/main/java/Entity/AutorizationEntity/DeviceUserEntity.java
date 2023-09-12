package Entity.AutorizationEntity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name="deviceuser")
public class DeviceUserEntity {

	public DeviceUserEntity() {
		
	}

	public DeviceUserEntity(CompositePrimaryKey primaryKey) {
		this.primaryKey=primaryKey;
	}
	@Column(name="validtokenuuid")
	private String validTokenId;
	
	@Column(name="lastActivity")
	private Timestamp lastActivity;
	
	@Column(name="is_user_current_active")
	private boolean isUserCurrentActive;
	@EmbeddedId
	private CompositePrimaryKey primaryKey;
	
	
	public String getValidTokenId() {
		return validTokenId;
	}

	
	public void setValidTokenId(String validTokenId) {
		this.validTokenId = validTokenId;
	}

	public Timestamp getLastActivity() {
		return lastActivity;
	}

	public void setLastActivity(Timestamp lastActivity) {
		this.lastActivity = lastActivity;
	}

	public CompositePrimaryKey getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(CompositePrimaryKey primaryKey) {
		this.primaryKey = primaryKey;
	}

	
	
	public boolean isUserCurrentActive() {
		return isUserCurrentActive;
	}


	public void setUserCurrentActive(boolean isUserCurrentActive) {
		this.isUserCurrentActive = isUserCurrentActive;
	}



	@Embeddable
	public static class CompositePrimaryKey implements Serializable {
		@Column(name="deviceuuid")
		private String deviceId;
		
		@Column(name="userUUID")
		private int userId;

		public String getDeviceId() {
			return deviceId;
		}

		public void setDeviceId(String deviceId) {
			this.deviceId = deviceId;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}
		
	
	}



}
