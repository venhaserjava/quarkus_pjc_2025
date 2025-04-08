package unidade.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UnidadeRequest(
        @NotBlank
        @Size(max = 200)
        String nome,

        @NotBlank
        @Size(max = 20)
        String sigla
) {}
