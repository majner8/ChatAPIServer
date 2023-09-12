package Autorization.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import DTO.Autorization.AutorizationRequestDTO;

public class AutorizationDTOValidator implements ConstraintValidator<AutorizationSurNameValidator, AutorizationRequestDTO> {

	@Override
	public boolean isValid(AutorizationRequestDTO value, ConstraintValidatorContext context) {

		
		
		if(value.getEmail()==null&&(value.getCountryPreflix()==null||value.getPhone()==null)) {
			return false;
		}
		if(value.getEmail()!=null&&(value.getCountryPreflix()!=null||value.getPhone()!=null)){
			return false;
		}
		if(value.getCountryPreflix()!=null||value.getPhone()!=null) {
			return false;
		}	
		
		
		
		
		return true;
	}

	

}
