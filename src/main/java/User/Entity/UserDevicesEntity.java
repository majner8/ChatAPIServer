package User.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name="deviceuser")
public class UserDevicesEntity {


	@Column(name="validtokenuuid")
	private String validTokenId;
	@Column(name="ipadress")
	private String iptAdres;
	public String getValidTokenId() {
		return validTokenId;
	}

	public void setValidTokenId(String validTokenId) {
		this.validTokenId = validTokenId;
	}

	public String getIptAdres() {
		return iptAdres;
	}

	public void setIptAdres(String iptAdres) {
		this.iptAdres = iptAdres;
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

	@Column(name="lastActivity")
	private Timestamp lastActivity;
	@EmbeddedId
	private CompositePrimaryKey primaryKey;
	
	@Embeddable
	public static class CompositePrimaryKey implements Serializable {
		@Column(name="deviceuuid")
		private int deviceId;
		
		@Column(name="userUUID")
		private int userId;

		public int getDeviceId() {
			return deviceId;
		}

		public void setDeviceId(int deviceId) {
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
