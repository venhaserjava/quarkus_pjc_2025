package unidade.dtos;

//package com.rossatti.quarkus_pjc_2025.unidade.dtos;

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
