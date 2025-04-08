package cidade.entities;

import endereco.entities.Endereco;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(
        name = "cidade",
        uniqueConstraints = @UniqueConstraint(columnNames = {"cid_nome", "cid_uf"})
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid_id")
    @ToString.Include
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "cid_nome", length = 200, nullable = false)
    @ToString.Include
    private String nome;

    @Column(name = "cid_uf", length = 2, nullable = false)
    private String uf;

    @PrePersist
    @PreUpdate
    private void formatarUf() {
        this.uf = this.uf != null ? this.uf.toUpperCase() : null;
    }

    @OneToMany(mappedBy = "cidade", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Endereco> enderecos;
}
