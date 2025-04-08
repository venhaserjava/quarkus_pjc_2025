package pessoa_foto.dtos;

import java.time.LocalDate;

public record PessoaFotoResponse(
        Long id,
        LocalDate data,
        String bucket,
        String hash
) {}
