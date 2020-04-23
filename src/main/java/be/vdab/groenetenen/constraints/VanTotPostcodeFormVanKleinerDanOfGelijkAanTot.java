package be.vdab.groenetenen.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VanTotPostcodeFormVanKleinerDanOfGelijkAanTotValidator.class)
public @interface VanTotPostcodeFormVanKleinerDanOfGelijkAanTot {
    String message() default "{be.vdab.groenetenen.constraints.VanKleinerDanOfGelijkAanTot.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
