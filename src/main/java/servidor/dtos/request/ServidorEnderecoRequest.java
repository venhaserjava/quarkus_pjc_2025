package servidor.dtos.request;


import jakarta.validation.constraints.*;
import cidade.validators.ValidUF;

public record ServidorEnderecoRequest(
        @NotBlank @Size(max = 50)
        @Pattern(regexp = "Rua|Avenida|Travessa|Alameda|Praça|Rodovia|Estrada|Aeroporto|Campo|Chácara|Colônia|Condomínio|Conjunto|Distrito|Esplanada|Estação|Fazenda")
        String tipoLogradouro,

        @NotBlank @Size(max = 200) String logradouro,
        @NotNull @Min(1) int numero,
        @NotBlank @Size(max = 100) String bairro,
        @NotNull ServidorCidadeRequest cidade
) {}
