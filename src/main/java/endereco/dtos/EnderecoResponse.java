package endereco.dtos;

//package com.rossatti.quarkus_pjc_2025.endereco.dtos;

public record EnderecoResponse(
        Long id,
        String tipoLogradouro,
        String logradouro,
        Integer numero,
        String bairro,
        Long cidadeId
) {}
