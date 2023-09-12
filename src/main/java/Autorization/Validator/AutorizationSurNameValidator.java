package Autorization.Validator;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = AutorizationDTOValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AutorizationSurNameValidator {
    String message() default "Phone and prefix must be both present or both absent.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
