package endereco.repositories;

import endereco.entities.Endereco;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Optional;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<Endereco> {


    public Optional<Endereco> findByTipoLogradouroAndLogradouroAndNumeroAndBairroAndCidadeId(
            String tipoLogradouro,
            String logradouro,
            @NotBlank @Size(max = 200) String logradouroed, Integer numero,
            String bairro,
            Long cidadeId
    ) {
        return find("tipoLogradouro = ?1 and  logradouro = ?2 and numero = ?3 and bairro = ?4 and cidade.id = ?5",
                tipoLogradouro,logradouro, numero, bairro, cidadeId).firstResultOptional();
    }

    public boolean existsByLogradouroNumeroBairroCidade(String logradouro, Integer numero, String bairro, Long cidadeId) {
        return find("logradouro = ?1 and numero = ?2 and bairro = ?3 and cidade.id = ?4",
                logradouro, numero, bairro, cidadeId)
                .firstResultOptional()
                .isPresent();
    }

}
