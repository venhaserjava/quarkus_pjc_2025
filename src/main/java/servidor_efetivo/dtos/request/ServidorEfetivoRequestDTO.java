package servidor_efetivo.dtos.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ServidorEfetivoRequestDTO(
        @NotNull Long pessoaId,
        @NotBlank String matricula,
        @NotNull Long unidadeId,
        @NotNull LocalDate dataLotacao,
        String portaria
) {}
