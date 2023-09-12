package Autorization.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class AutorizationPasswordValidator implements ConstraintValidator<AutorizationPasswordInterface, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
	
		return true;
	}



	

}
	

