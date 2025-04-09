package servidor_efetivo.entities;

import jakarta.persistence.*;
import lombok.*;
import pessoa.entities.Pessoa;

@Entity
@Table(name = "servidor_efetivo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServidorEfetivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "se_id")
    private Long id;

    @Column(name = "se_matricula", nullable = false, length = 20)
    private String matricula;

    @OneToOne
    @JoinColumn(name = "pes_id", referencedColumnName = "pes_id", nullable = false)
    private Pessoa pessoa;
}
