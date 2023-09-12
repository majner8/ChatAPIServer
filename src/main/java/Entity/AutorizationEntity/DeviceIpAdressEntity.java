package Entity.AutorizationEntity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import Entity.AutorizationEntity.DeviceUserEntity.CompositePrimaryKey;

@Entity(name="device_ip_adress")
public class DeviceIpAdressEntity {

	public DeviceIpAdressEntity() {
		
	}

	public DeviceIpAdressEntity(CompositePrimaryKey primaryKey) {
		this.primaryKey=primaryKey;
	}
	
	@Column(name="ipadress")
	private String IpAdres;
	
	@Column(name="lastActivity")
	private Timestamp lastActivity;
	
	@EmbeddedId
	private CompositePrimaryKey primaryKey;

	public String getIpAdres() {
		return IpAdres;
	}

	public void setIpAdres(String ipAdres) {
		IpAdres = ipAdres;
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
	
	
	
	
}
