package DTO.Autorization;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import Authorization.Validator.AutorizationPasswordInterface;
import Authorization.Validator.AutorizationSurNameValidator;



@AutorizationSurNameValidator
public class AutorizationRequestDTO {

	@Email
	private String email;
	@Size(max=3)
	private String countryPreflix;
	@Size(max=12)
	private String phone;
	@NotEmpty
	@AutorizationPasswordInterface
	private String password;
	@NotEmpty
	@NotNull
	private String deviceID;
	@NotNull
	private Boolean isDeviceNew;
	
	
	public Boolean getIsDeviceNew() {
		return isDeviceNew;
	}
	public void setIsDeviceNew(Boolean isDeviceNew) {
		this.isDeviceNew = isDeviceNew;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	
	
}

