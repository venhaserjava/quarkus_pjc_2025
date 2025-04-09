package servidor_temporario.dtos.reponse;

import java.time.LocalDate;

public record ServidorTemporarioDTO(
        Long id,
        String nome,
        String mae,
        String pai,
        String sexo,
        LocalDate dataNascimento,
        String tipoLogradouro,
        String logradouro,
        Integer numero,
        String bairro,
        String cidadeNome,
        String tipoServidor,
        LocalDate dataAdmissao,
        LocalDate dataDemissao
) {}
