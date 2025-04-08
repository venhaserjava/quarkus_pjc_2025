package lotacao.entities;


import jakarta.persistence.*;
import lombok.*;
import pessoa.entities.Pessoa;
import unidade.entities.Unidade;

import java.time.LocalDate;

@Entity
@Table(name = "lotacao")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lot_id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    @ManyToOne(optional = false)
    @JoinColumn(name = "unid_id")
    private Unidade unidade;

    @Column(name = "lot_data_lotacao", nullable = false)
    private LocalDate dataLotacao;

    @Column(name = "lot_data_remocao")
    private LocalDate dataRemocao;

    @Column(name = "lot_portaria", length = 100)
    private String portaria;
}
