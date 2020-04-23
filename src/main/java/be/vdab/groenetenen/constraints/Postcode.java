package be.vdab.groenetenen.constraints;

import org.hibernate.validator.constraints.Range;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

// composition of other bean validations

//@Target({METHOD, FIELD, ANNOTATION_TYPE})
//@Retention(RUNTIME)
//@Constraint(validatedBy = {})
//@Range(min = 1000, max = 9999)
//public @interface Postcode {
//    @OverridesAttribute(constraint = Range.class, name = "message")
//    String message() default "{be.vdab.groenetenen.constraints.Postcode.message}";
//    Class<?>[] groups() default {};
//    Class<? extends Payload>[] payload() default {};
//}

// own bean validation

@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PostcodeValidator.class)
public @interface Postcode{
    String message () default "{be.vdab.groenetenen.constraints.Postcode.message}";
    Class<?>[] groups () default {};
    Class<? extends Payload>[] payload () default {};
}
