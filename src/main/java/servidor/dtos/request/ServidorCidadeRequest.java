package servidor.dtos.request;

import jakarta.validation.constraints.*;
import cidade.validators.ValidUF;

public record ServidorCidadeRequest(
        @NotBlank @Size(min = 3, max = 200) String nome,
        @NotBlank @Size(min = 2, max = 2) @ValidUF String uf
) {}
