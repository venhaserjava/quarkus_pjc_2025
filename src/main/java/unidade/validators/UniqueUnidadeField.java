package unidade.validators;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueUnidadeFieldValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUnidadeField {
    String message() default "Valor já existente para o campo informado.";
    String fieldName(); // exemplo: "nome" ou "sigla"
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
