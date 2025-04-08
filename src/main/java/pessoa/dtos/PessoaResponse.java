package pessoa.dtos;

//package com.rossatti.quarkus_pjc_2025.pessoa.dtos;

import java.time.LocalDate;

public record PessoaResponse(
        Long id,
        String nome,
        String mae,
        String pai,
        String sexo,
        LocalDate dataNascimento
) {}

