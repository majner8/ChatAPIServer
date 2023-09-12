package Entity.AutorizationEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;


@Entity(name="Securitymonitoring")
public class SecurityMonitoring {

	@Column(name="email")
	private String email;
	@Column(name="country_preflix")
	private String countryPreflix;
	@Column(name="phone")
	private String phone;
	@Column(name="attempt_sucesfull")
	private boolean attempSucesful;
	@Size(max=15)
	@Column(name="ip_adress")
	private String IPV4Adress;
	
	@Id
	@Column
	private int id;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountryPreflix() {
		return countryPreflix;
	}

	public void setCountryPreflix(String countryPreflix) {
		this.countryPreflix = countryPreflix;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isAttempSucesful() {
		return attempSucesful;
	}

	public void setAttempSucesful(boolean attempSucesful) {
		this.attempSucesful = attempSucesful;
	}

	public String getIPV4Adress() {
		return IPV4Adress;
	}

	public void setIPV4Adress(String iPV4Adress) {
		IPV4Adress = iPV4Adress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
