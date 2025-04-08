package lotacao.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import lotacao.dtos.response.LotacaoResponse;
import lotacao.entities.Lotacao;

@ApplicationScoped
public class LotacaoMapper {

    public LotacaoResponse toResponse(Lotacao l) {
        return new LotacaoResponse(
                l.getId(),
                l.getPessoa().getId(),
                l.getPessoa().getNome(),
                l.getUnidade().getId(),
                l.getUnidade().getNome(),
                l.getDataLotacao(),
                l.getDataRemocao(),
                l.getPortaria()
        );
    }
}
