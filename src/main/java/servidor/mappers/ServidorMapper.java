package servidor.mappers;

import pessoa.entities.Pessoa;
import servidor.dtos.request.ServidorRequest;
import servidor.dtos.response.ServidorResponse;

public class ServidorMapper {

    public static ServidorResponse toResponse(Pessoa pessoa) {
        var endereco = pessoa.getEnderecos().iterator().next();
        return new ServidorResponse(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getMae(),
                pessoa.getPai(),
                pessoa.getSexo(),
//                pessoa.getDataNascimento(),
                endereco.getTipoLogradouro(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getCidade().getNome(),
                null // link da foto será preenchido depois
        );
    }
    public static Pessoa toEntity(ServidorRequest request)    {
        return Pessoa.builder()
                .nome(request.getNome())
                .mae(request.getMae())
                .pai(request.getPai())
                .sexo(request.getSexo())
//                .dataNascimento(request.getDataNascimento())
                .build();
    }
    /*
    public static Pessoa toEntity(ServidorRequest request) {
        return Pessoa.builder()
                .nome(request.nome())
                .mae(request.mae())
                .pai(request.pai())
                .sexo(request.sexo())
                .dataNascimento(request.dataNascimento())
                .build();
    }

     */
}
