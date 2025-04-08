package lotacao.validators;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lotacao.dtos.request.LotacaoRequest;
import pessoa.repositories.PessoaRepository;

public class LotacaoDateValidator implements ConstraintValidator<ValidLotacaoDate, LotacaoRequest> {

    @Inject
    PessoaRepository pessoaRepository;

    @Override
    public boolean isValid(LotacaoRequest request, ConstraintValidatorContext context) {
        if (request == null || request.pessoaId() == null || request.dataLotacao() == null) return false;

        return pessoaRepository.findByIdOptional(request.pessoaId())
                .map(p -> {
                    boolean valid = request.dataLotacao().isAfter(p.getDataNascimento());
                    if (!valid) {
                        context.disableDefaultConstraintViolation();
                        context.buildConstraintViolationWithTemplate(
                                "A data de lotação (" + request.dataLotacao() +
                                        ") deve ser posterior à data de nascimento da pessoa (" +
                                        p.getDataNascimento() + ")."
                        ).addConstraintViolation();
                    }
                    return valid;
                }).orElse(false);
    }
}
