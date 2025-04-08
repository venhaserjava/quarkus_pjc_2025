package cidade;

import cidade.entities.Cidade;
import cidade.services.CidadeService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CidadeServiceTest {

    @Inject
    CidadeService cidadeService;

    private static Long cidadeId;

    @Test
    @Order(1)
    void shouldCreateCity() {
        Cidade cidade = Cidade.builder()
                .nome("Curitiba")
                .uf("pr")
                .build();

        Cidade created = cidadeService.create(cidade);
        cidadeId = created.getId();

        assertThat(created.getId()).isNotNull();
        assertThat(created.getUf()).isEqualTo("PR"); // Verifica formatação
    }

    @Test
    @Order(2)
    void shouldFindCityById() {
        var cidade = cidadeService.findById(cidadeId);
        assertThat(cidade).isPresent();
    }

    @Test
    @Order(3)
    void shouldUpdateCity() {
        Cidade update = Cidade.builder()
                .nome("Curitiba")
                .uf("sc")
                .build();

        Cidade updated = cidadeService.update(cidadeId, update);
        assertThat(updated.getUf()).isEqualTo("SC");
    }

    @Test
    @Order(4)
    void shouldListAllCities() {
        List<Cidade> cidades = cidadeService.findAll();
        assertThat(cidades).isNotEmpty();
    }

    @Test
    @Order(5)
    void shouldDeleteCity() {
        cidadeService.delete(cidadeId);
        assertThat(cidadeService.findById(cidadeId)).isEmpty();
    }
}
