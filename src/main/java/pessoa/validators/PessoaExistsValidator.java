package pessoa.validators;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pessoa.repositories.PessoaRepository;

public class PessoaExistsValidator implements ConstraintValidator<ValidPessoaExists, Long> {

    @Inject
    PessoaRepository repository;

    @Override
    public boolean isValid(Long pessoaId, ConstraintValidatorContext context) {
        if (pessoaId == null) return false;

        boolean exists = repository.findByIdOptional(pessoaId).isPresent();

        if (!exists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "A pessoa com ID " + pessoaId + " não foi encontrada."
            ).addConstraintViolation();
        }

        return exists;
    }
}
