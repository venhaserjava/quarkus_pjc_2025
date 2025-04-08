package unidade.mappers;

import unidade.dtos.UnidadeRequest;
import unidade.dtos.UnidadeResponse;
import unidade.entities.Unidade;

public class UnidadeMapper {

    public static Unidade toEntity(UnidadeRequest request) {
        Unidade u = new Unidade();
        u.setNome(request.nome());
        u.setSigla(request.sigla());
        return u;
    }

    public static UnidadeResponse toResponse(Unidade u) {
        return new UnidadeResponse(u.getId(), u.getNome(), u.getSigla());
    }

    public static void updateEntity(Unidade u, UnidadeRequest request) {
        u.setNome(request.nome());
        u.setSigla(request.sigla());
    }
}
