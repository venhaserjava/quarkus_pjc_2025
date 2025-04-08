package unidade.services;

//package com.rossatti.quarkus_pjc_2025.unidade.services;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import unidade.dtos.UnidadeRequest;
import unidade.dtos.UnidadeResponse;
import unidade.entities.Unidade;
import unidade.mappers.UnidadeMapper;
import unidade.repositories.UnidadeRepository;

//import com.rossatti.quarkus_pjc_2025.unidade.dtos.*;
//import com.rossatti.quarkus_pjc_2025.unidade.entities.Unidade;
//import com.rossatti.quarkus_pjc_2025.unidade.mappers.UnidadeMapper;
//import com.rossatti.quarkus_pjc_2025.unidade.repositories.UnidadeRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UnidadeService {

    private final UnidadeRepository repository;

    public UnidadeService(UnidadeRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public UnidadeResponse create(UnidadeRequest request) {
        Unidade unidade = UnidadeMapper.toEntity(request);
        repository.persist(unidade);
        return UnidadeMapper.toResponse(unidade);
    }

    public List<UnidadeResponse> findAll() {
        return repository.listAll().stream()
                .map(UnidadeMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UnidadeResponse findById(Long id) {
        return UnidadeMapper.toResponse(repository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("Unidade not found")));
    }

    @Transactional
    public UnidadeResponse update(Long id, UnidadeRequest request) {
        Unidade unidade = repository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("Unidade not found"));
        UnidadeMapper.updateEntity(unidade, request);
        return UnidadeMapper.toResponse(unidade);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

