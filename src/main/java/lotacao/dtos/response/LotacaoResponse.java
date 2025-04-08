package lotacao.dtos.response;

import java.time.LocalDate;

public record LotacaoResponse(
        Long id,
        Long pessoaId,
        String pessoaNome,
        Long unidadeId,
        String unidadeNome,
        LocalDate dataLotacao,
        LocalDate dataRemocao,
        String portaria
) {}
