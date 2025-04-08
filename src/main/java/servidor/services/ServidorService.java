package servidor.services;

import cidade.entities.Cidade;
import cidade.repositories.CidadeRepository;
import endereco.entities.Endereco;
import endereco.repositories.EnderecoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import pessoa.entities.Pessoa;
import pessoa.repositories.PessoaRepository;
import servidor.dtos.request.ServidorRequest;
import servidor.mappers.ServidorMapper;

@ApplicationScoped
public class ServidorService {

    @Inject
    PessoaRepository pessoaRepository;
    @Inject
    CidadeRepository cidadeRepository;
    @Inject
    EnderecoRepository enderecoRepository;

    @Transactional
    public Pessoa createServidor(ServidorRequest request) {
        var enderecoDto = request.enderecos().iterator().next();
        var cidadeDto = enderecoDto.cidade();

        // CIDADE
        var cidade = cidadeRepository.find("nome = ?1 and uf = ?2", cidadeDto.nome(), cidadeDto.uf())
                .firstResultOptional()
                .orElseGet(() -> {
                    var nova = new Cidade();
                    nova.setNome(cidadeDto.nome());
                    nova.setUf(cidadeDto.uf());
                    cidadeRepository.persist(nova);
                    return nova;
                });

        // ENDEREÇO
        var endereco = enderecoRepository.find("tipoLogradouro = ?1 and logradouro = ?2 and numero = ?3 and bairro = ?4 and cidade.id = ?5",
                        enderecoDto.tipoLogradouro(), enderecoDto.logradouro(), enderecoDto.numero(),
                        enderecoDto.bairro(), cidade.getId())
                .firstResultOptional()
                .orElseGet(() -> {
                    var novo = new Endereco();
                    novo.setTipoLogradouro(enderecoDto.tipoLogradouro());
                    novo.setLogradouro(enderecoDto.logradouro());
                    novo.setNumero(enderecoDto.numero());
                    novo.setBairro(enderecoDto.bairro());
                    novo.setCidade(cidade);
                    enderecoRepository.persist(novo);
                    return novo;
                });

        // PESSOA
        var pessoa = pessoaRepository.find("nome = ?1 and mae = ?2 and dataNascimento = ?3",
                        request.nome(), request.mae(), request.dataNascimento())
                .firstResultOptional()
                .orElseGet(() -> {
                    var nova = ServidorMapper.toEntity(request);
                    pessoaRepository.persist(nova);
                    return nova;
                });

        pessoa.getEnderecos().add(endereco);
        pessoaRepository.persist(pessoa);

        return pessoa;
    }
}
