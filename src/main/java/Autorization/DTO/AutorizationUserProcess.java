package Autorization.DTO;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="User")
public class AutorizationUserProcess {

	@Column()
	private String email;
	@Column()
	private String country_preflix;
	@Column
	private String phone;
	@Id
	@Column(name="useruuid")
	private int userId; 
	@Column(name="sername")
	private String serName;
	@Column(name="lastname")
	private String lastName;
	@Column()
	private Timestamp birthDay;
	
}
