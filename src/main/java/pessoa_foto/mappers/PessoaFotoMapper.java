package pessoa_foto.mappers;

import pessoa.entities.Pessoa;
import pessoa_foto.dtos.PessoaFotoRequest;
import pessoa_foto.dtos.PessoaFotoResponse;
import pessoa_foto.entities.PessoaFoto;

public class PessoaFotoMapper {

    public static PessoaFoto toEntity(PessoaFotoRequest request, Pessoa pessoa) {
        return new PessoaFoto(null, pessoa, request.data(), request.bucket(), request.hash());
    }

    public static PessoaFotoResponse toResponse(PessoaFoto entity) {
        return new PessoaFotoResponse(entity.getId(), entity.getData(), entity.getBucket(), entity.getHash());
    }
}
