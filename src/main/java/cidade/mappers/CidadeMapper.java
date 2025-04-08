package cidade.mappers;

//package com.rossatti.quarkus_pjc_2025.cidade.mappers;
//import com.rossatti.quarkus_pjc_2025.cidade.dtos.*;
//import com.rossatti.quarkus_pjc_2025.cidade.entities.Cidade;

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
