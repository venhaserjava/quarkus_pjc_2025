package pessoa.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PessoaExistsValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPessoaExists {
    String message() default "A pessoa com o ID informado não existe.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
