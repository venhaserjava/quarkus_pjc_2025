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
) {
    public ServidorResponse withFoto(String fotoUrl) {
        return new ServidorResponse(
                id, nome, mae, pai, sexo, dataNascimento,
                tipoLogradouro, logradouro, numero, bairro,
                cidadeNome, fotoUrl
        );
    }
}
