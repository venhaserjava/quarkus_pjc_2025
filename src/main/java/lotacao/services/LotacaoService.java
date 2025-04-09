package lotacao.services;


import commons.dtos.PagedResponseDTO;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lotacao.dtos.request.LotacaoRequest;
import lotacao.dtos.response.EnderecoFuncionalResponse;
import lotacao.dtos.response.LotacaoResponse;
import lotacao.entities.Lotacao;
import lotacao.mappers.LotacaoMapper;
import lotacao.repositories.LotacaoRepository;
import pessoa.entities.Pessoa;
import pessoa.repositories.PessoaRepository;
import unidade.entities.Unidade;
import unidade.repositories.UnidadeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class LotacaoService {

    @Inject LotacaoRepository lotacaoRepository;
    @Inject PessoaRepository pessoaRepository;
    @Inject UnidadeRepository unidadeRepository;
    @Inject LotacaoMapper mapper;

    @Transactional
    public LotacaoResponse create(LotacaoRequest request) {
        Pessoa pessoa = pessoaRepository.findById(request.pessoaId());
        Unidade unidade = unidadeRepository.findById(request.unidadeId());

        Lotacao lotacao = Lotacao.builder()
                .pessoa(pessoa)
                .unidade(unidade)
                .dataLotacao(request.dataLotacao())
                .dataRemocao(request.dataRemocao())
                .portaria(request.portaria())
                .build();

        lotacaoRepository.persist(lotacao);
        return mapper.toResponse(lotacao);
    }

    public Optional<LotacaoResponse> findById(Long id) {
        return lotacaoRepository.findByIdOptional(id).map(mapper::toResponse);
    }

    public PagedResponseDTO<LotacaoResponse> findAll(int page, int size) {
        var query = lotacaoRepository.findAll().page(Page.of(page, size));
        List<LotacaoResponse> content = query.list().stream().map(mapper::toResponse).collect(Collectors.toList());
        return new PagedResponseDTO<>(content, page, size, query.count());
    }

    public List<EnderecoFuncionalResponse> buscarEnderecoFuncionalPorNome(String nome) {
        return lotacaoRepository.buscarPorPessoaNome(nome).stream().map(lotacao -> {
            var unidade = lotacao.getUnidade();
            var unidadeEndereco = unidade.getUnidadeEndereco();
            var endereco = unidadeEndereco != null ? unidadeEndereco.getEndereco() : null;

            return new EnderecoFuncionalResponse(
                    lotacao.getPessoa().getNome(),
                    unidade.getNome(),
                    endereco != null ? endereco.getLogradouro() : "Não informado",
                    endereco != null ? endereco.getNumero() : 0,
                    endereco != null ? endereco.getBairro() : "Não informado",
                    endereco != null ? endereco.getCidade().getNome() : "Não informado",
                    endereco != null ? endereco.getCidade().getUf() : "Não informado"
            );
        }).toList();
    }

}
