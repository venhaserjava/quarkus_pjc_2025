package unidade.mappers;

import endereco.entities.Endereco;
import unidade.dtos.UnidadeRequest;
import unidade.dtos.UnidadeResponse;
import unidade.entities.Unidade;

public class UnidadeMapper {

    public static Unidade toEntity(UnidadeRequest request) {
        Unidade u = new Unidade();
        u.setNome(request.getNome());
        u.setSigla(request.getSigla());
        return u;
    }

    public static UnidadeResponse toResponse(Unidade u) {
        return new UnidadeResponse(u.getId(), u.getNome(), u.getSigla());
    }

    /*
    public static UnidadeResponse toResponse(Unidade unidade, Endereco endereco) {
        return new UnidadeResponse(
                unidade.getId(),
                unidade.getNome(),
                unidade.getSigla(),
                endereco.toString() // ou montar um DTO próprio
        );
    }
    */
    public static void updateEntity(Unidade u, UnidadeRequest request) {
        u.setNome(request.getNome());
        u.setSigla(request.getSigla());
    }
}
