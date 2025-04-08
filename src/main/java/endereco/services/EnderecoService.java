package endereco.services;

//package com.rossatti.quarkus_pjc_2025.services;
//import com.rossatti.quarkus_pjc_2025.entities.Endereco;
//import com.rossatti.quarkus_pjc_2025.repositories.EnderecoRepository;
import endereco.entities.Endereco;
import endereco.repository.EnderecoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EnderecoService {

    @Inject
    EnderecoRepository enderecoRepository;

    public List<Endereco> findAll() {
        return enderecoRepository.listAll();
    }

    public Optional<Endereco> findById(Long id) {
        return enderecoRepository.findByIdOptional(id);
    }

    @Transactional
    public Endereco create(Endereco endereco) {
        enderecoRepository.persist(endereco);
        return endereco;
    }

    @Transactional
    public Endereco update(Long id, Endereco updated) {
        Endereco endereco = enderecoRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Address not found"));

        endereco.setTipoLogradouro(updated.getTipoLogradouro());
        endereco.setLogradouro(updated.getLogradouro());
        endereco.setNumero(updated.getNumero());
        endereco.setBairro(updated.getBairro());
        endereco.setCidade(updated.getCidade());

        return endereco;
    }

    @Transactional
    public void delete(Long id) {
        boolean deleted = enderecoRepository.deleteById(id);
        if (!deleted) {
            throw new NotFoundException("Address not found");
        }
    }

    public static class NotFoundException extends RuntimeException {
        public NotFoundException(String message) {
            super(message);
        }
    }
}
