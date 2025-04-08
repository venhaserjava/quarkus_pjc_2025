package cidade.services;
//package com.rossatti.quarkus_pjc_2025.cidade.services;
import cidade.dtos.CidadeRequest;
import cidade.dtos.CidadeResponse;
import cidade.entities.Cidade;
import cidade.mappers.CidadeMapper;
import cidade.repositories.CidadeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

//import com.rossatti.quarkus_pjc_2025.cidade.dtos.*;
//import com.rossatti.quarkus_pjc_2025.cidade.entities.Cidade;
//import com.rossatti.quarkus_pjc_2025.cidade.mappers.CidadeMapper;
//import com.rossatti.quarkus_pjc_2025.cidade.repositories.CidadeRepository;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CidadeService {

    private final CidadeRepository repository;

    public CidadeService(CidadeRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CidadeResponse create(CidadeRequest request) {
        Cidade cidade = CidadeMapper.toEntity(request);
        repository.persist(cidade);
        return CidadeMapper.toResponse(cidade);
    }

    public List<CidadeResponse> findAll() {
        return repository.listAll().stream()
                .map(CidadeMapper::toResponse)
                .collect(Collectors.toList());
    }

    public CidadeResponse findById(Long id) {
        return CidadeMapper.toResponse(repository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("Cidade not found")));
    }

    @Transactional
    public CidadeResponse update(Long id, CidadeRequest request) {
        Cidade cidade = repository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("Cidade not found"));
        CidadeMapper.updateEntity(cidade, request);
        return CidadeMapper.toResponse(cidade);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
