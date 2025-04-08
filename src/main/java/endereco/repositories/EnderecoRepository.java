package endereco.repositories;

import endereco.entities.Endereco;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<Endereco> {
    public boolean existsByLogradouroNumeroBairroCidade(String logradouro, Integer numero, String bairro, Long cidadeId) {
        return find("logradouro = ?1 and numero = ?2 and bairro = ?3 and cidade.id = ?4",
                logradouro, numero, bairro, cidadeId).firstResultOptional().isPresent();
    }
}
