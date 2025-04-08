package pessoa.entities;

import endereco.entities.Endereco;
import lotacao.entities.Lotacao;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Lotacao> lotacoes;

    @ManyToMany
    @JoinTable(
            name = "pessoa_endereco",
            joinColumns = @JoinColumn(name = "pes_id"),
            inverseJoinColumns = @JoinColumn(name = "end_id")
    )
    @Builder.Default
    private Set<Endereco> enderecos = new HashSet<>();
}
