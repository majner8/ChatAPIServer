package Autorization.Validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = AutorizationPasswordValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AutorizationPasswordInterface {
    String message() default "Phone and prefix must be both present or both absent.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
