package endereco.dtos;
//package com.rossatti.quarkus_pjc_2025.endereco.dtos;

import jakarta.validation.constraints.*;

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
