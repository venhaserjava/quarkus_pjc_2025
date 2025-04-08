package unidade.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UnidadeExistsValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUnidadeExists {
    String message() default "A unidade com o ID informado não existe.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
