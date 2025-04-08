package cidade.dtos;
//package com.rossatti.quarkus_pjc_2025.cidade.dtos;

public record CidadeResponse(
        Long id,
        String nome,
        String uf
) {}
