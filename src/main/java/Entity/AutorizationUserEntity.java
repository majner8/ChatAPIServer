package Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AutorizationUserEntity {

	@Column(name="email")
	private String email;
	@Column(name="country_preflix")
	private String country_preflix;
	@Column(name="phone")
	private String phone;
	@Id
	@Column(name="useruuid")
	private int userId;
	@Column(name="Sername")
	private String serName;
	@Column(name="lastname")
	private String lastName;
	@Column(name="birthday")
	private Date birthDay;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountry_preflix() {
		return country_preflix;
	}
	public void setCountry_preflix(String country_preflix) {
		this.country_preflix = country_preflix;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getSerName() {
		return serName;
	}
	public void setSerName(String serName) {
		this.serName = serName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	
	

	
}
