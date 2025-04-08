package unidade.services;


import commons.dtos.PagedResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import unidade.dtos.UnidadeRequest;
import unidade.dtos.UnidadeResponse;
import unidade.entities.Unidade;
import unidade.mappers.UnidadeMapper;
import unidade.repositories.UnidadeRepository;
import io.quarkus.panache.common.Page;
//import common.dtos.PagedResponseDTO;


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

    public PagedResponseDTO<UnidadeResponse> findAll(int page, int size) {
        var query = repository.findAll().page(Page.of(page, size));

        List<UnidadeResponse> content = query.list()
                .stream()
                .map(UnidadeMapper::toResponse)
                .collect(Collectors.toList());

        long totalElements = query.count();
        return new PagedResponseDTO<>(content, page, size, totalElements);
    }

//
//    public List<UnidadeResponse> findAll() {
//        return repository.listAll().stream()
//                .map(UnidadeMapper::toResponse)
//                .collect(Collectors.toList());
//    }

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

