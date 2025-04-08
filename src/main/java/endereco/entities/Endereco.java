package endereco.entities;
//package com.rossatti.quarkus_pjc_2025.entities;

import cidade.entities.Cidade;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "endereco",
        uniqueConstraints = @UniqueConstraint(columnNames = {"end_logradouro", "end_bairro", "end_numero", "cid_id"})
)
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "end_id")
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column(name = "end_tipo_logradouro", length = 50, nullable = false)
    private String tipoLogradouro;

    @Column(name = "end_logradouro", length = 200, nullable = false)
    @ToString.Include
    private String logradouro;

    @Column(name = "end_numero", nullable = false)
    private Integer numero;

    @Column(name = "end_bairro", length = 100, nullable = false)
    private String bairro;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "cid_id", referencedColumnName = "cid_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_endereco_cidade"))
    private Cidade cidade;
}
