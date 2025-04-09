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

    public List<Lotacao> buscarPorPessoaNome(String nome) {
        return getEntityManager().createQuery("""
            SELECT l FROM Lotacao l
            JOIN FETCH l.pessoa p
            JOIN FETCH l.unidade u
            JOIN FETCH u.unidadeEndereco ue
            JOIN FETCH ue.endereco e
            JOIN FETCH e.cidade c
            WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))
        """, Lotacao.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    
    public List<Lotacao> findByUnidadeId(Long unidadeId, int page, int size) {
        return find("unidade.id", unidadeId)
               .page(page, size)
               .list();
    }

    

}
