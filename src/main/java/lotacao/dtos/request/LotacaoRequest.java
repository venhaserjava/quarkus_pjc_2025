package lotacao.dtos.request;


import jakarta.validation.constraints.*;
import lotacao.validators.ValidLotacaoDate;
import pessoa.validators.ValidPessoaExists;
import unidade.validators.ValidUnidadeExists;

import java.time.LocalDate;

@ValidLotacaoDate
public record LotacaoRequest(
        @NotNull @ValidPessoaExists Long pessoaId,
        @NotNull @ValidUnidadeExists Long unidadeId,
        @NotNull LocalDate dataLotacao,
        LocalDate dataRemocao,
        @NotBlank @Size(min = 1, max = 100) String portaria
) {}
