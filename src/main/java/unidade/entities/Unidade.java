package unidade.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "unidade",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "unid_nome"),
                @UniqueConstraint(columnNames = "unid_sigla")
        }
)
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unid_id")
    @ToString.Include
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "unid_nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "unid_sigla", nullable = false, length = 20)
    private String sigla;

    @OneToOne(mappedBy = "unidade", cascade = CascadeType.ALL)
    private UnidadeEndereco unidadeEndereco;

}
