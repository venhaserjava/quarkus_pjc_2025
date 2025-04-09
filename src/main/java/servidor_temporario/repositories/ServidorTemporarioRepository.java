package servidor_temporario.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import servidor_temporario.entities.ServidorTemporario;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ServidorTemporarioRepository implements PanacheRepository<ServidorTemporario> {

    public boolean existsByPessoaIdAndDataDemissaoIsNull(Long pessoaId) {
        return find("pessoaId = ?1 and dataDemissao is null", pessoaId).firstResultOptional().isPresent();
    }

    public Optional<ServidorTemporario> findByPessoaIdAndDataDemissaoIsNull(Long pessoaId) {
        return find("pessoaId = ?1 and dataDemissao is null", pessoaId).firstResultOptional();
    }

    public List<ServidorTemporario> findAllByDataDemissaoIsNull(int page, int size) {
        return find("dataDemissao is null").page(page, size).list();
    }

    public List<ServidorTemporario> findAllByPessoaNomeContainingIgnoreCaseAndDataDemissaoIsNull(String nome, int page, int size) {
        return find("LOWER(pessoa.nome) LIKE ?1 and dataDemissao is null", "%" + nome.toLowerCase() + "%")
                .page(page, size).list();
    }
}
