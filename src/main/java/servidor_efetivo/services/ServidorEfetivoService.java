package servidor_efetivo.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import lotacao.entities.Lotacao;
import lotacao.repositories.LotacaoRepository;
import pessoa.entities.Pessoa;
import pessoa.repositories.PessoaRepository;
import servidor_efetivo.dtos.request.ServidorEfetivoRequestDTO;
import servidor_efetivo.dtos.response.ServidorEfetivoResponseDTO;
import servidor_efetivo.entities.ServidorEfetivo;
import servidor_efetivo.repositories.ServidorEfetivoRepository;
import unidade.entities.Unidade;
import unidade.repositories.UnidadeRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class ServidorEfetivoService {

    private final ServidorEfetivoRepository servidorEfetivoRepository;
    private final PessoaRepository pessoaRepository;
    private final UnidadeRepository unidadeRepository;
    private final LotacaoRepository lotacaoRepository;

    public ServidorEfetivoService(ServidorEfetivoRepository servidorEfetivoRepository,
                                  PessoaRepository pessoaRepository,
                                  UnidadeRepository unidadeRepository,
                                  LotacaoRepository lotacaoRepository) {
        this.servidorEfetivoRepository = servidorEfetivoRepository;
        this.pessoaRepository = pessoaRepository;
        this.unidadeRepository = unidadeRepository;
        this.lotacaoRepository = lotacaoRepository;
    }

    @Transactional
    public ServidorEfetivoResponseDTO criar(ServidorEfetivoRequestDTO dto) {
        List<String> erros = new ArrayList<>();

        if (servidorEfetivoRepository.existsByPessoaId(dto.pessoaId())) {
            erros.add("Servidor efetivo já cadastrado!");
        }

        Pessoa pessoa = pessoaRepository.findById(dto.pessoaId());
        if (pessoa == null) erros.add("Pessoa não encontrada.");

        Unidade unidade = unidadeRepository.findById(dto.unidadeId());
        if (unidade == null) erros.add("Unidade não encontrada.");

        if (pessoa != null && pessoa.getDataNascimento().plusYears(18).isAfter(dto.dataLotacao())) {
            erros.add("A data de lotação deve ser posterior ao 18º aniversário.");
        }

        if (dto.portaria() == null || dto.portaria().isBlank()) {
            erros.add("Portaria é obrigatória e não pode estar vazia.");
        }

        if (!erros.isEmpty()) {
            throw new WebApplicationException(erroJson(erros), Response.Status.BAD_REQUEST);
        }

        ServidorEfetivo servidor = new ServidorEfetivo();
        servidor.setMatricula(dto.matricula());
        servidor.setPessoa(pessoa);
        servidorEfetivoRepository.persist(servidor);

        Lotacao lotacao = new Lotacao();
        lotacao.setPessoa(pessoa);
        lotacao.setUnidade(unidade);
        lotacao.setDataLotacao(dto.dataLotacao());
        lotacao.setPortaria(dto.portaria());
        lotacaoRepository.persist(lotacao);

        return new ServidorEfetivoResponseDTO(
                servidor.getId(),
                pessoa.getNome(),
                calcularIdade(pessoa.getDataNascimento()),
                servidor.getMatricula(),
                unidade.getNome(),
                dto.dataLotacao(),
                dto.portaria()
        );
    }
    
    public List<ServidorEfetivoResponseDTO> listarPorUnidade(Long unidadeId, int page, int size) {
        List<Lotacao> lotacoes = lotacaoRepository.findByUnidadeId(unidadeId, page, size);
        List<Long> pessoaIds = lotacoes.stream()
                .map(l -> l.getPessoa().getId())
                .toList();

        // Buscar todos os servidores efetivos em lote
        Map<Long, ServidorEfetivo> servidoresMap = servidorEfetivoRepository
                .findByPessoaIdIn(pessoaIds)
                .stream()
                .collect(Collectors.toMap(se -> se.getPessoa().getId(), se -> se));

        return lotacoes.stream()
                .map(lotacao -> {
                    ServidorEfetivo se = servidoresMap.get(lotacao.getPessoa().getId());
                    if (se == null) {
                        throw new WebApplicationException("Servidor efetivo não encontrado para pessoa ID " + lotacao.getPessoa().getId(), Response.Status.NOT_FOUND);
                    }
                    return new ServidorEfetivoResponseDTO(
                            se.getId(),
                            lotacao.getPessoa().getNome(),
                            calcularIdade(lotacao.getPessoa().getDataNascimento()),
                            se.getMatricula(),
                            lotacao.getUnidade().getNome(),
                            lotacao.getDataLotacao(),
                            lotacao.getPortaria()
                    );
                }).toList();
    }



    private int calcularIdade(LocalDate dataNascimento) {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    private String erroJson(List<String> erros) {
        return erros.size() == 1
                ? "{\"erro\": \"" + erros.get(0) + "\"}"
                : "{\"erros\": " + erros.toString() + "}";
    }
}
