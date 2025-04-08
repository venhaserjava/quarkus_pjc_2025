package unidade.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unidade.entities.Unidade;

@ApplicationScoped
public class UnidadeRepository implements PanacheRepository<Unidade> {
}
