package endereco.validators;

import cidade.repositories.CidadeRepository;
import endereco.dtos.EnderecoRequest;
import endereco.repositories.EnderecoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@ApplicationScoped
public class UniqueEnderecoValidator implements ConstraintValidator<UniqueEndereco, EnderecoRequest> {

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    CidadeRepository cidadeRepository;

    @Override
    public boolean isValid(EnderecoRequest request, ConstraintValidatorContext context) {
        if (request == null || request.cidadeId() == null) return true;

        return !enderecoRepository.existsByLogradouroNumeroBairroCidade(
                request.logradouro(),
                request.numero(),
                request.bairro(),
                request.cidadeId()
        );
    }
}
