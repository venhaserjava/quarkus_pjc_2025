package cidade.validators;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueCidadeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCidade {
    String message() default "Já existe uma cidade com este nome e UF.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
