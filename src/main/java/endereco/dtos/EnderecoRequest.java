package endereco.dtos;

import endereco.validators.UniqueEndereco;
import jakarta.validation.constraints.*;

@UniqueEndereco
public record EnderecoRequest(
        @NotBlank @Size(max = 50)
        String tipoLogradouro,

        @NotBlank @Size(max = 200)
        String logradouro,

        @NotNull
        Integer numero,

        @NotBlank @Size(max = 100)
        String bairro,

        @NotNull
        Long cidadeId
) {}
