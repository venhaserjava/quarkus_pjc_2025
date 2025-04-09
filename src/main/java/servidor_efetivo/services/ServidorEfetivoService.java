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

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private int calcularIdade(LocalDate dataNascimento) {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    private String erroJson(List<String> erros) {
        return erros.size() == 1
                ? "{\"erro\": \"" + erros.get(0) + "\"}"
                : "{\"erros\": " + erros.toString() + "}";
    }
}
