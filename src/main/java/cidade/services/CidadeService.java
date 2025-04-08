package cidade.services;
//package com.rossatti.quarkus_pjc_2025.services;
//import com.rossatti.quarkus_pjc_2025.entities.Cidade;
//import com.rossatti.quarkus_pjc_2025.repositories.CidadeRepository;
import cidade.entities.Cidade;
import cidade.repositories.CidadeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CidadeService {

    @Inject
    CidadeRepository cidadeRepository;

    public List<Cidade> findAll() {
        return cidadeRepository.listAll();
    }

    public Optional<Cidade> findById(Long id) {
        return cidadeRepository.findByIdOptional(id);
    }

    @Transactional
    public Cidade create(Cidade cidade) {
        cidadeRepository.persist(cidade);
        return cidade;
    }

    @Transactional
    public Cidade update(Long id, Cidade updated) {
        Cidade cidade = cidadeRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("City not found"));

        cidade.setNome(updated.getNome());
        cidade.setUf(updated.getUf());
        return cidade;
    }

    @Transactional
    public void delete(Long id) {
        boolean deleted = cidadeRepository.deleteById(id);
        if (!deleted) {
            throw new NotFoundException("City not found");
        }
    }

    public static class NotFoundException extends RuntimeException {
        public NotFoundException(String message) {
            super(message);
        }
    }
}
