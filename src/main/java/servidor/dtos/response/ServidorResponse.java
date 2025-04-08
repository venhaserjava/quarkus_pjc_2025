package servidor.dtos.response;

import java.time.LocalDate;

public record ServidorResponse(
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
        String foto
) {}
