package unidade.services;

//package com.rossatti.quarkus_pjc_2025.unidade.services;
//import com.rossatti.quarkus_pjc_2025.unidade.entities.Unidade;
//import com.rossatti.quarkus_pjc_2025.unidade.repositories.UnidadeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import unidade.entities.Unidade;
import unidade.repositories.UnidadeRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UnidadeService {

    @Inject
    UnidadeRepository repository;

    public List<Unidade> findAll() {
        return repository.listAll();
    }

    public Optional<Unidade> findById(Long id) {
        return repository.findByIdOptional(id);
    }

    @Transactional
    public Unidade create(Unidade unidade) {
        repository.persist(unidade);
        return unidade;
    }

    @Transactional
    public Unidade update(Long id, Unidade unidadeData) {
        Unidade unidade = repository.findById(id);
        if (unidade == null) {
            return null;
        }
        unidade.setNome(unidadeData.getNome());
        unidade.setSigla(unidadeData.getSigla());
        return unidade;
    }

    @Transactional
    public boolean delete(Long id) {
        return repository.deleteById(id);
    }
}
