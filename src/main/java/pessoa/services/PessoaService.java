package pessoa.services;

//package com.rossatti.quarkus_pjc_2025.pessoa.services;
//import com.rossatti.quarkus_pjc_2025.pessoa.entities.Pessoa;
//import com.rossatti.quarkus_pjc_2025.pessoa.dtos.PessoaRequest;
//import com.rossatti.quarkus_pjc_2025.pessoa.dtos.PessoaResponse;
//import com.rossatti.quarkus_pjc_2025.pessoa.mappers.PessoaMapper;
//import com.rossatti.quarkus_pjc_2025.pessoa.repositories.PessoaRepository;

import commons.dtos.PagedResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import pessoa.dtos.PessoaRequest;
import pessoa.dtos.PessoaResponse;
import pessoa.entities.Pessoa;
import pessoa.mappers.PessoaMapper;
import pessoa.repositories.PessoaRepository;
//import io.quarkus.hibernate.orm.panache.common.Page;
import io.quarkus.panache.common.Page;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@ApplicationScoped
public class PessoaService {

    @Inject
    PessoaRepository repository;

    public PagedResponseDTO<PessoaResponse> listAllPaged(int page, int size) {
//        var panachePage = io.quarkus.hibernate.orm.panache.common.Page.of(page, size);
        var panachePage = Page.of(page, size);
        var query = repository.findAll().page(panachePage);

        List<PessoaResponse> content = query.list()
                .stream()
                .map(PessoaMapper::toResponse)
                .collect(Collectors.toList());

        long totalElements = query.count();
        return new PagedResponseDTO<>(content, page, size, totalElements);
    }

    public Optional<PessoaResponse> findById(Long id) {
        return repository.findByIdOptional(id)
                .map(PessoaMapper::toResponse);
    }

    @Transactional
    public PessoaResponse create(PessoaRequest request) {
        Pessoa pessoa = PessoaMapper.toEntity(request);
        repository.persist(pessoa);
        return PessoaMapper.toResponse(pessoa);
    }

    @Transactional
    public PessoaResponse update(Long id, PessoaRequest request) {
        Pessoa pessoa = repository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("Pessoa not found"));

        PessoaMapper.updateEntity(pessoa, request);
        return PessoaMapper.toResponse(pessoa);
    }

    @Transactional
    public boolean delete(Long id) {
        return repository.deleteById(id);
    }
}



/*
@ApplicationScoped
public class PessoaService {

    @Inject
    PessoaRepository repository;

    public List<PessoaResponse> listAll() {
        return repository.listAll()
                .stream()
                .map(PessoaMapper::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<PessoaResponse> findById(Long id) {
        return repository.findByIdOptional(id)
                .map(PessoaMapper::toResponse);
    }

    @Transactional
    public PessoaResponse create(PessoaRequest request) {
        Pessoa pessoa = PessoaMapper.toEntity(request);
        repository.persist(pessoa);
        return PessoaMapper.toResponse(pessoa);
    }

    @Transactional
    public PessoaResponse update(Long id, PessoaRequest request) {
        Pessoa pessoa = repository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("Pessoa not found"));

        PessoaMapper.updateEntity(pessoa, request);
        return PessoaMapper.toResponse(pessoa);
    }

    @Transactional
    public boolean delete(Long id) {
        return repository.deleteById(id);
    }
}
*/