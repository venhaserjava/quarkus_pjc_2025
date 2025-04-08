package pessoa_foto.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import pessoa_foto.entities.PessoaFoto;

import java.util.List;

@ApplicationScoped
public class PessoaFotoRepository implements PanacheRepository<PessoaFoto> {

    public List<PessoaFoto> findByPessoaIdOrdered(Long pessoaId) {
        return find("pessoa.id = ?1 ORDER BY data DESC", pessoaId).list();
    }
}
