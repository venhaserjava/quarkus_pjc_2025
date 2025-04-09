package servidor_temporario.dtos.reponse;

import java.time.LocalDate;

public record ServidorTemporarioResponse(
        Long pessoaId,
        String pessoaNome,
        String pessoaMae,
        String pessoaPai,
        String sexo,
        LocalDate dataNascimento,
        String tipoServidor,
        LocalDate dataAdmissao,
        LocalDate dataDemissao
) {}
