package Autorization.Entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name="deviceuser")
public class AutorizationDeviceProcess {


	@Column(name="validtokenuuid")
	private String validTokenId;
	@Column(name="validtokenuuid")
	private String iptAdres;
	@Column(name="validtokenuuid")
	private Timestamp lastActivity;
	@EmbeddedId
	private CompositePrimaryKey primaryKey;
	
	@Embeddable
	public static class CompositePrimaryKey {
		@Column(name="deviceuuid")
		private int deviceId;
		
		@Column(name="userUUID")
		private int userId;
		
		
	}



}
