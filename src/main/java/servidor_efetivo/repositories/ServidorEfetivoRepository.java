package servidor_efetivo.repositories;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import servidor_efetivo.entities.ServidorEfetivo;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ServidorEfetivoRepository implements PanacheRepository<ServidorEfetivo> {

    public boolean existsByPessoaId(Long pessoaId) {
        return find("pessoa.id", pessoaId).firstResultOptional().isPresent();
    }

    public Optional<ServidorEfetivo> findByPessoaId(Long pessoaId) {
        return find("pessoa.id", pessoaId).firstResultOptional();
    }

    public List<ServidorEfetivo> findByPessoaIdIn(List<Long> pessoaIds) {
        return list("pessoa.id IN ?1", pessoaIds);
    }
}
