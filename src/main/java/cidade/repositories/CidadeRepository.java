package cidade.repositories;

import cidade.entities.Cidade;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

@ApplicationScoped
public class CidadeRepository implements PanacheRepository<Cidade> {

    public PanacheQuery<Cidade> findAllCities(int page, int size) {
        return findAll().page(Page.of(page, size));
    }

    public boolean existsByNomeAndUf(String nome, String uf) {
        return find("nome = ?1 and uf = ?2", nome, uf).firstResultOptional().isPresent();
    }
    public Cidade findByNomeAndUf(String nome, String uf) {
        return find("nome = ?1 and uf = ?2", nome, uf).firstResult();
    }

//    public PanacheQuery<Cidade> findAll(int page, int size) {
//        return findAll(page, size);
//        return findAll(Page.of(page, size));
//    }
    // Métodos específicos para Cidade podem ser adicionados aqui, se necessário.
    // Exemplo:
}
//import cidade.entities.Cidade;
//import io.quarkus.hibernate.orm.panache.PanacheRepository;
//
//public class CidadeRepository implements PanacheRepository<Cidade> {
//}
