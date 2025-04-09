package unidade.repositories;

import endereco.entities.Endereco;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unidade.entities.Unidade;
import unidade.entities.UnidadeEndereco;

import java.util.Optional;

@ApplicationScoped
public class UnidadeEnderecoRepository implements PanacheRepository<UnidadeEndereco> {

    public Optional<UnidadeEndereco> findByUnidade(Unidade unidade) {
        return find("unidade", unidade).firstResultOptional();
    }
    public Endereco findEnderecoByUnidade(Unidade unidade) {
        return find("unidade", unidade)
                .firstResultOptional()
                .map(UnidadeEndereco::getEndereco)
                .orElse(null); // ou lançar exceção se for obrigatório
    }
}
