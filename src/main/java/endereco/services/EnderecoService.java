package endereco.services;

import cidade.repositories.CidadeRepository;
import commons.dtos.PagedResponseDTO;
import endereco.dtos.EnderecoRequest;
import endereco.dtos.EnderecoResponse;
import endereco.entities.Endereco;
import endereco.mappers.EnderecoMapper;
import endereco.repository.EnderecoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import io.quarkus.panache.common.Page;


import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class EnderecoService {

    private final EnderecoRepository repository;
    private final CidadeRepository cidadeRepository;

    public EnderecoService(EnderecoRepository repository, CidadeRepository cidadeRepository) {
        this.repository = repository;
        this.cidadeRepository = cidadeRepository;
    }

    @Transactional
    public EnderecoResponse create(EnderecoRequest request) {
        var cidade = cidadeRepository.findByIdOptional(request.cidadeId())
                .orElseThrow(() -> new RuntimeException("Cidade not found"));
        Endereco endereco = EnderecoMapper.toEntity(request, cidade);
        repository.persist(endereco);
        return EnderecoMapper.toResponse(endereco);
    }

    public PagedResponseDTO<EnderecoResponse> findAll(int page, int size) {
        var query = repository.findAll().page(Page.of(page, size));

        List<EnderecoResponse> content = query.list()
                .stream()
                .map(EnderecoMapper::toResponse)
                .collect(Collectors.toList());

        long totalElements = query.count();
        return new PagedResponseDTO<>(content, page, size, totalElements);
    }

/*
    public List<EnderecoResponse> findAll() {
        return repository.listAll().stream()
                .map(EnderecoMapper::toResponse)
                .collect(Collectors.toList());
    }
*/
    public EnderecoResponse findById(Long id) {
        return EnderecoMapper.toResponse(repository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("Endereco not found")));
    }

    @Transactional
    public EnderecoResponse update(Long id, EnderecoRequest request) {
        var endereco = repository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("Endereco not found"));
        var cidade = cidadeRepository.findByIdOptional(request.cidadeId())
                .orElseThrow(() -> new RuntimeException("Cidade not found"));
        EnderecoMapper.updateEntity(endereco, request, cidade);
        return EnderecoMapper.toResponse(endereco);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

