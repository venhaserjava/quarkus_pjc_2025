package servidor_temporario.dtos.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ServidorTemporarioRequest(

        @NotNull(message = "O ID da pessoa é obrigatório.")
        Long pessoaId,
        LocalDate dataAdmissao,
        LocalDate dataDemissao
) {}
