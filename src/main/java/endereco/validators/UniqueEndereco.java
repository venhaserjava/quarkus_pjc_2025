package endereco.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEnderecoValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEndereco {
    String message() default "Este endereço já está cadastrado!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
