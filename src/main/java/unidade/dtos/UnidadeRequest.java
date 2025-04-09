package unidade.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import servidor.dtos.request.ServidorEnderecoRequest;
import unidade.validators.UniqueUnidadeField;

import java.util.Set;

public record UnidadeRequest(
        @NotBlank @Size(min = 3, max = 200)
        @UniqueUnidadeField(fieldName = "nome", message = "Este Nome já está em uso.")
        String nome,

        @NotBlank @Size(min = 2, max = 200)
        @UniqueUnidadeField(fieldName = "sigla", message = "Esta Sigla já está em uso.")
        Set<ServidorEnderecoRequest> enderecos
) {}


/*
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
*/