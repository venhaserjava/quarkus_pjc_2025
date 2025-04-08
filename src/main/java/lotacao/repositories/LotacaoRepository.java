package lotacao.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lotacao.entities.Lotacao;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class LotacaoRepository implements PanacheRepository<Lotacao> {

    public List<Lotacao> findByDataLotacaoAfter(LocalDate data) {
        return find("dataLotacao > ?1", data).list();
    }

    public long countByUnidadeId(Long unidadeId) {
        return count("unidade.id", unidadeId);
    }
}
