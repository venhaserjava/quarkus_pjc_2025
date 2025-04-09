package servidor_temporario.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import pessoa.entities.Pessoa;
import servidor_temporario.dtos.reponse.ServidorTemporarioDTO;
import servidor_temporario.dtos.reponse.ServidorTemporarioResponse;
import servidor_temporario.entities.ServidorTemporario;

@ApplicationScoped
public class ServidorTemporarioMapper {

    public ServidorTemporarioResponse toResponse(Pessoa pessoa, ServidorTemporario servidor) {
        return new ServidorTemporarioResponse(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getMae(),
                pessoa.getPai(),
                pessoa.getSexo(),
                pessoa.getDataNascimento(),
                "Servidor Temporário",
                servidor.getDataAdmissao(),
                servidor.getDataDemissao()
        );
    }
    public ServidorTemporarioDTO toDTO(ServidorTemporario servidor) {
        Pessoa pessoa = servidor.getPessoa();
        var endereco = (pessoa.getEnderecos() != null && !pessoa.getEnderecos().isEmpty())
                ? pessoa.getEnderecos().iterator().next()
                : null;

        return new ServidorTemporarioDTO(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getMae(),
                pessoa.getPai(),
                pessoa.getSexo(),
                pessoa.getDataNascimento(),
                (endereco != null) ? endereco.getTipoLogradouro() : null,
                (endereco != null) ? endereco.getLogradouro() : null,
                (endereco != null) ? endereco.getNumero() : null,
                (endereco != null) ? endereco.getBairro() : null,
                (endereco != null && endereco.getCidade() != null) ? endereco.getCidade().getNome() : null,
                "Servidor_Temporário",
                servidor.getDataAdmissao(),
                servidor.getDataDemissao()
        );
    }
}
