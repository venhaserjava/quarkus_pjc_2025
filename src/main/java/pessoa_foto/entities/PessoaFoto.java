package pessoa_foto.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pessoa.entities.Pessoa;

import java.time.LocalDate;

@Entity
@Table(name = "foto_pessoa", uniqueConstraints = @UniqueConstraint(columnNames = "fp_hash"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PessoaFoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fp_id")
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pes_id", nullable = false)
    private Pessoa pessoa;

    @Column(name = "fp_data", nullable = false)
    private LocalDate data;

    @Column(name = "fp_bucket", length = 50, nullable = false)
    private String bucket;

    @Column(name = "fp_hash", length = 50, nullable = false, unique = true)
    private String hash;
}
