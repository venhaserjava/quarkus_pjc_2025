package pessoa.dtos;
//package com.rossatti.quarkus_pjc_2025.pessoa.dtos;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record PessoaRequest(

        @NotBlank
        @Size(min = 3, max = 200)
        String nome,

        @NotBlank
        @Size(min = 2, max = 200)
        String mae,

        @Size(min = 3, max = 200)
        String pai,

        @NotBlank
        @Pattern(regexp = "MASCULINO|FEMININO|NAOINF", message = "Sexo deve ser MASCULINO, FEMININO ou NAOINF")
        String sexo,

        @NotNull
        @Past
        LocalDate dataNascimento
) {}
