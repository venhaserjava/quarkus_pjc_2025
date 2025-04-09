package unidade.entities;

import endereco.entities.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "unidade_endereco")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeEndereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "unid_id", referencedColumnName = "unid_id", nullable = false, unique = true)
    private Unidade unidade;

    @ManyToOne
    @JoinColumn(name = "end_id", referencedColumnName = "end_id", nullable = false)
    private Endereco endereco;
}
