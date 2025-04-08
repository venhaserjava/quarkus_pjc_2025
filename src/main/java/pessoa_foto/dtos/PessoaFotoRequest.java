package pessoa_foto.dtos;

import java.time.LocalDate;

public record PessoaFotoRequest(
        Long pessoaId,
        LocalDate data,
        String bucket,
        String hash
) {}