package endereco;

//package com.rossatti.quarkus_pjc_2025.services;
//import com.rossatti.quarkus_pjc_2025.entities.Cidade;
//import com.rossatti.quarkus_pjc_2025.entities.Endereco;

import cidade.entities.Cidade;
import cidade.services.CidadeService;
import endereco.entities.Endereco;
import endereco.services.EnderecoService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EnderecoServiceTest {

    @Inject
    CidadeService cidadeService;

    @Inject
    EnderecoService enderecoService;

    private static Long cidadeId;
    private static Long enderecoId;

    @Test
    @Order(1)
    void shouldCreateCityAndAddress() {
        Cidade cidade = cidadeService.create(
                Cidade.builder().nome("Londrina").uf("pr").build()
        );
        cidadeId = cidade.getId();

        Endereco endereco = Endereco.builder()
                .tipoLogradouro("Rua")
                .logradouro("Avenida Paraná")
                .numero(123)
                .bairro("Centro")
                .cidade(cidade)
                .build();

        Endereco created = enderecoService.create(endereco);
        enderecoId = created.getId();

        assertThat(created.getId()).isNotNull();
        assertThat(created.getCidade()).isNotNull();
        assertThat(created.getCidade().getId()).isEqualTo(cidadeId);
    }

    @Test
    @Order(2)
    void shouldFindAddressById() {
        var endereco = enderecoService.findById(enderecoId);
        assertThat(endereco).isPresent();
    }

    @Test
    @Order(3)
    void shouldUpdateAddress() {
        Endereco updatedData = Endereco.builder()
                .tipoLogradouro("Avenida")
                .logradouro("Brasil")
                .numero(999)
                .bairro("Jardim")
                .cidade(Cidade.builder().id(cidadeId).build()) // evita reload completo
                .build();

        Endereco updated = enderecoService.update(enderecoId, updatedData);
        assertThat(updated.getLogradouro()).isEqualTo("Brasil");
        assertThat(updated.getNumero()).isEqualTo(999);
    }

    @Test
    @Order(4)
    void shouldListAllAddresses() {
        List<Endereco> all = enderecoService.findAll();
        assertThat(all).isNotEmpty();
    }

    @Test
    @Order(5)
    void shouldDeleteAddress() {
        enderecoService.delete(enderecoId);
        assertThat(enderecoService.findById(enderecoId)).isEmpty();
    }

    @Test
    @Order(6)
    void shouldDeleteCityAfterAddress() {
        cidadeService.delete(cidadeId);
        assertThat(cidadeService.findById(cidadeId)).isEmpty();
    }
}
