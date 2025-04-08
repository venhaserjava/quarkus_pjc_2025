package cidade.services;

import cidade.dtos.CidadeRequest;
import cidade.dtos.CidadeResponse;
import cidade.entities.Cidade;
import cidade.mappers.CidadeMapper;
import cidade.repositories.CidadeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class CidadeService {

    @Inject
    CidadeRepository cidadeRepository;

    @Transactional
    public CidadeResponse create(CidadeRequest request) {
        Cidade cidade = CidadeMapper.toEntity(request);
        cidadeRepository.persist(cidade);
        return CidadeMapper.toResponse(cidade);
    }

    public List<CidadeResponse> findAll(int page, int size) {
        //return cidadeRepository.findAll(Page.of(page, size))
        return cidadeRepository.findAll(page,size)
                .list() // Chama o método list() no PanacheQuery retornado
                .stream()
                .map(CidadeMapper::toResponse)
                .collect(Collectors.toList());
    }

    public CidadeResponse findById(Long id) {
        Cidade cidade = cidadeRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Cidade não encontrada com o ID: " + id));
        return CidadeMapper.toResponse(cidade);
    }

    @Transactional
    public CidadeResponse update(Long id, CidadeRequest request) {
        Cidade cidade = cidadeRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Cidade não encontrada com o ID: " + id));
        CidadeMapper.updateEntity(cidade, request);
        cidadeRepository.persist(cidade);
        return CidadeMapper.toResponse(cidade);
    }

    @Transactional
    public void delete(Long id) {
        Cidade cidade = cidadeRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Cidade não encontrada com o ID: " + id));
        cidadeRepository.delete(cidade);
    }
}/*
import cidade.dtos.CidadeRequest;
import cidade.dtos.CidadeResponse;
import cidade.entities.Cidade;
import cidade.mappers.CidadeMapper;
import cidade.repositories.CidadeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

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
*/