package pessoa.entities;

//package com.rossatti.quarkus_pjc_2025.pessoa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "pessoa",
        uniqueConstraints = @UniqueConstraint(columnNames = "pes_nome")
)
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pes_id")
    @ToString.Include
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "pes_nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "pes_data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "pes_mae", nullable = false, length = 200)
    private String mae;

    @Column(name = "pes_pai", length = 200)
    private String pai;

    @Column(name = "pes_sexo", nullable = false, length = 10)
    private String sexo;
}
