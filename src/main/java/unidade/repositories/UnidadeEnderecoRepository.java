package unidade.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unidade.entities.UnidadeEndereco;

@ApplicationScoped
public class UnidadeEnderecoRepository implements PanacheRepository<UnidadeEndereco> {
}
