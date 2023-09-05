package Autorization.DTO.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import Autorization.DTO.AutorizationRequestDTO;

public class AutorizationDTOValidator implements ConstraintValidator<AutorizationSurNameValidator, AutorizationRequestDTO> {

	@Override
	public boolean isValid(AutorizationRequestDTO value, ConstraintValidatorContext context) {

		for(int i=0;i<=10;i++) {
			System.out.println("C");
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
		
		
		
		
		return true;
	}

	

}
