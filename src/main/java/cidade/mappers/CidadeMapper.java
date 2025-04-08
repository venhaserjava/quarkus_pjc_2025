package cidade.mappers;

import cidade.dtos.CidadeRequest;
import cidade.dtos.CidadeResponse;
import cidade.entities.Cidade;

public class CidadeMapper {

    public static Cidade toEntity(CidadeRequest request) {
        Cidade cidade = new Cidade();
        cidade.setNome(request.nome());
        cidade.setUf(request.uf().toUpperCase());
        return cidade;
    }

    public static CidadeResponse toResponse(Cidade cidade) {
        return new CidadeResponse(cidade.getId(), cidade.getNome(), cidade.getUf());
    }

    public static void updateEntity(Cidade cidade, CidadeRequest request) {
        cidade.setNome(request.nome());
        cidade.setUf(request.uf().toUpperCase());
    }
}
