package unidade.validators;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import unidade.repositories.UnidadeRepository;

public class UnidadeExistsValidator implements ConstraintValidator<ValidUnidadeExists, Long> {

    @Inject
    UnidadeRepository repository;

    @Override
    public boolean isValid(Long unidadeId, ConstraintValidatorContext context) {
        if (unidadeId == null) return false;

        boolean exists = repository.findByIdOptional(unidadeId).isPresent();

        if (!exists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "A unidade com ID " + unidadeId + " não foi encontrada."
            ).addConstraintViolation();
        }
        return exists;
    }
}
