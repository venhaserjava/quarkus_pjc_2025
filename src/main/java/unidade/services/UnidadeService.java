package unidade.services;


import cidade.entities.Cidade;
import cidade.repositories.CidadeRepository;
import commons.dtos.PagedResponseDTO;
import endereco.entities.Endereco;
import endereco.repositories.EnderecoRepository;
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
    private final Cidade cidade;
    private final CidadeRepository cidadeRepository;
    private final Endereco endereco;
    private final EnderecoRepository enderecoRepository;

    public UnidadeService(
            UnidadeRepository repository,
            Cidade cidade,
            CidadeRepository cidadeRepository,
            Endereco endereco,
            EnderecoRepository enderecoRepository
    ) {

        this.repository = repository;
        this.cidade = cidade;
        this.cidadeRepository = cidadeRepository;
        this.endereco = endereco;
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public UnidadeResponse create(UnidadeRequest request) {
        var dtoEndereco = request.enderecos().iterator().next();
        var cidade = cidadeRepository.findByNomeAndUf(dtoEndereco.cidade().nome(), dtoEndereco.cidade().uf())
                .orElseGet(() -> {
                    var nova = new Cidade();
                    nova.setNome(dtoEndereco.cidade().nome());
                    nova.setUf(dtoEndereco.cidade().uf());
                    cidadeRepository.persist(nova);
                    return nova;
                });

        var endereco = enderecoRepository.find("tipoLogradouro = ?1 and logradouro = ?2 and numero = ?3 and bairro = ?4 and cidade.id = ?5",
                        dtoEndereco.tipoLogradouro(), dtoEndereco.logradouro(), dtoEndereco.numero(), dtoEndereco.bairro(), cidade.getId())
                .firstResultOptional()
                .orElseGet(() -> {
                    var novo = Endereco.builder()
                            .tipoLogradouro(dtoEndereco.tipoLogradouro())
                            .logradouro(dtoEndereco.logradouro())
                            .numero(dtoEndereco.numero())
                            .bairro(dtoEndereco.bairro())
                            .cidade(cidade)
                            .build();
                    enderecoRepository.persist(novo);
                    return novo;
                });

        var unidade = Unidade.builder()
                .nome(request.nome())
                .sigla(request.sigla())
                .build();
        unidadeRepository.persist(unidade);

        var unidadeEndereco = new UnidadeEndereco(null, unidade, endereco);
        unidadeEnderecoRepository.persist(unidadeEndereco);
        unidade.setUnidadeEndereco(unidadeEndereco);

        return unidadeMapper.toResponse(unidade);
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


//    @Transactional
//    public UnidadeResponse create(UnidadeRequest request) {
//        Unidade unidade = UnidadeMapper.toEntity(request);
//        repository.persist(unidade);
//        return UnidadeMapper.toResponse(unidade);
//    }

//
//    public List<UnidadeResponse> findAll() {
//        return repository.listAll().stream()
//                .map(UnidadeMapper::toResponse)
//                .collect(Collectors.toList());
//    }
