package cidade.dtos;
//package com.rossatti.quarkus_pjc_2025.cidade.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CidadeRequest(
        @NotBlank
        @Size(max = 200)
        String nome,

        @NotBlank
        @Size(min = 2, max = 2)
        String uf
) {}
