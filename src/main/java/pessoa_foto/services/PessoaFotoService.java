package pessoa_foto.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import pessoa.entities.Pessoa;
import pessoa.repositories.PessoaRepository;
import pessoa_foto.dtos.PessoaFotoRequest;
import pessoa_foto.dtos.PessoaFotoResponse;
import pessoa_foto.entities.PessoaFoto;
import pessoa_foto.mappers.PessoaFotoMapper;
import pessoa_foto.repositories.PessoaFotoRepository;

import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PessoaFotoService {

    @Inject
    PessoaFotoRepository repository;

    @Inject
    PessoaRepository pessoaRepository;

    @Transactional
    public PessoaFotoResponse create(PessoaFotoRequest request) {
        Pessoa pessoa = pessoaRepository.findByIdOptional(request.pessoaId())
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));
        PessoaFoto foto = PessoaFotoMapper.toEntity(request, pessoa);
        repository.persist(foto);
        return PessoaFotoMapper.toResponse(foto);
    }

    public List<String> listarHashesPorPessoa(Long pessoaId) {
        return repository.findByPessoaIdOrdered(pessoaId).stream()
                .map(PessoaFoto::getHash)
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveFoto(Long pessoaId, String hash) {
        Pessoa pessoa = pessoaRepository.findByIdOptional(pessoaId)
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));
        PessoaFoto foto = new PessoaFoto(null, pessoa, LocalDate.now(), "fotos", hash);
        repository.persist(foto);
    }

    public String generateHash(byte[] fileBytes) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(fileBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(hashBytes);
    }
}
