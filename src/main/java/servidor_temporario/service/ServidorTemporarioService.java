package servidor_temporario.service;

//package com.seuprojeto.servidor;
//import com.seuprojeto.dto.ServidorTemporarioDTO;
//import com.seuprojeto.dto.ServidorTemporarioRequest;
//import com.seuprojeto.dto.ServidorTemporarioResponse;
//import com.seuprojeto.entity.Pessoa;
//import com.seuprojeto.entity.ServidorTemporario;
//import com.seuprojeto.mapper.ServidorTemporarioMapper;
//import com.seuprojeto.repository.PessoaRepository;
//import com.seuprojeto.repository.ServidorEfetivoRepository;
//import com.seuprojeto.repository.ServidorTemporarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import pessoa.entities.Pessoa;
import pessoa.repositories.PessoaRepository;
import servidor_efetivo.repositories.ServidorEfetivoRepository;
import servidor_temporario.dtos.reponse.ServidorTemporarioDTO;
import servidor_temporario.dtos.reponse.ServidorTemporarioResponse;
import servidor_temporario.dtos.request.ServidorTemporarioRequest;
import servidor_temporario.entities.ServidorTemporario;
import servidor_temporario.mappers.ServidorTemporarioMapper;
import servidor_temporario.repositories.ServidorTemporarioRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ServidorTemporarioService {

    private final PessoaRepository pessoaRepository;
    private final ServidorTemporarioRepository servidorTemporarioRepository;
    private final ServidorEfetivoRepository servidorEfetivoRepository;
    private final ServidorTemporarioMapper mapper;

    public ServidorTemporarioService(
            PessoaRepository pessoaRepository,
            ServidorTemporarioRepository servidorTemporarioRepository,
            ServidorEfetivoRepository servidorEfetivoRepository,
            ServidorTemporarioMapper mapper) {
        this.pessoaRepository = pessoaRepository;
        this.servidorTemporarioRepository = servidorTemporarioRepository;
        this.servidorEfetivoRepository = servidorEfetivoRepository;
        this.mapper = mapper;
    }

    public ServidorTemporarioResponse findByPessoaId(Long pessoaId) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId);
        Optional<ServidorTemporario> servidorOpt = servidorTemporarioRepository.findByPessoaIdAndDataDemissaoIsNull(pessoaId);

        if (pessoa == null || servidorOpt.isEmpty()) {
            throw new WebApplicationException("Servidor temporário não encontrado", Response.Status.NOT_FOUND);
        }

        return mapper.toResponse(pessoa, servidorOpt.get());
    }

    public List<ServidorTemporarioDTO> findAll(String nome, int page, int size) {
        List<ServidorTemporario> lista = (nome != null && !nome.isBlank())
                ? servidorTemporarioRepository.findAllByPessoaNomeContainingIgnoreCaseAndDataDemissaoIsNull(nome, page, size)
                : servidorTemporarioRepository.findAllByDataDemissaoIsNull(page, size);

        return lista.stream().map(mapper::toDTO).toList();
    }

    @Transactional
    public void create(ServidorTemporarioRequest dto) {
        List<String> erros = validar(dto, false);
        if (!erros.isEmpty()) {
            throw new WebApplicationException(montarErro(erros), Response.Status.BAD_REQUEST);
        }

        ServidorTemporario novo = new ServidorTemporario();
        novo.setPessoaId(dto.pessoaId());
        novo.setDataAdmissao(dto.dataAdmissao());
        novo.setDataDemissao(dto.dataDemissao());
        servidorTemporarioRepository.persist(novo);
    }

    @Transactional
    public void update(Long pessoaId, ServidorTemporarioRequest dto) {
        List<String> erros = validar(dto, true);
        if (!erros.isEmpty()) {
            throw new WebApplicationException(montarErro(erros), Response.Status.BAD_REQUEST);
        }

        ServidorTemporario existente = servidorTemporarioRepository.findByPessoaIdAndDataDemissaoIsNull(pessoaId)
                .orElseThrow(() -> new WebApplicationException("Servidor temporário não encontrado", Response.Status.NOT_FOUND));

        existente.setDataAdmissao(dto.dataAdmissao());
        existente.setDataDemissao(dto.dataDemissao());
        servidorTemporarioRepository.persist(existente);
    }

    private List<String> validar(ServidorTemporarioRequest dto, boolean isUpdate) {
        List<String> erros = new ArrayList<>();
        Pessoa pessoa = pessoaRepository.findById(dto.pessoaId());

        if (pessoa == null) {
            erros.add("Pessoa não encontrada.");
        }

        if (servidorEfetivoRepository.existsByPessoaId(dto.pessoaId())) {
            erros.add("Este servidor é efetivo e não pode ser cadastrado como temporário.");
        }

        if (!isUpdate && servidorTemporarioRepository.existsByPessoaIdAndDataDemissaoIsNull(dto.pessoaId())) {
            erros.add("Servidor já cadastrado como temporário em vigência.");
        }

        if (!isMaiorDeIdade(pessoa, dto.dataAdmissao())) {
            erros.add("A data de admissão deve ser após o 18º aniversário.");
        }

        if (!isDemissaoValida(dto.dataAdmissao(), dto.dataDemissao())) {
            erros.add("A data de demissão deve ser após a data de admissão.");
        }

        return erros;
    }

    private boolean isMaiorDeIdade(Pessoa pessoa, LocalDate dataAdmissao) {
        if (pessoa == null || dataAdmissao == null) return false;
        return pessoa.getDataNascimento().plusYears(18).isBefore(dataAdmissao) || pessoa.getDataNascimento().plusYears(18).isEqual(dataAdmissao);
    }

    private boolean isDemissaoValida(LocalDate admissao, LocalDate demissao) {
        return demissao == null || admissao.isBefore(demissao);
    }

    private String montarErro(List<String> erros) {
        if (erros.size() == 1) {
            return "{\"erro\": \"" + erros.get(0) + "\"}";
        } else {
            return "{\"erros\": " + erros.toString() + "}";
        }
    }
}
