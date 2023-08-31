package Autorization.DTO.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import Autorization.DTO.AutorizationRequestDTO;

public class AutorizationDTOValidator implements ConstraintValidator<ValidAutorizationDTOInterface, AutorizationRequestDTO> {

	@Override
	public boolean isValid(AutorizationRequestDTO value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if(value.getDeviceID()==null) {
			return false;
		}
		if(value.getPassword()==null) {
			return false;
		}
		if(value.getEmail()==null&&(value.getCountryPreflix()==null||value.getPhone()==null)) {
			return false;
		}
		if(value.getEmail()!=null&&(value.getCountryPreflix()!=null||value.getPhone()!=null)){
			return false;
		}
		if(value.getCountryPreflix()!=null||value.getPhone()!=null) {
			return false;
		}	
		if(!this.validatePassword(value.getPassword())) {
			return false;
		}
		if(!this.validateEmail(value.getEmail())) {
			return false;
		}
		
		
		
		return true;
	}

	private boolean validatePassword(String password) {
		return true;
	}
	private boolean validateEmail(String email) {
		return true;
	}

}
