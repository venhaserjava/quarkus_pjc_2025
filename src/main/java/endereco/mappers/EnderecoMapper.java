package endereco.mappers;

//package com.rossatti.quarkus_pjc_2025.endereco.mappers;
//import com.rossatti.quarkus_pjc_2025.endereco.dtos.*;
//import com.rossatti.quarkus_pjc_2025.endereco.entities.Endereco;
//import com.rossatti.quarkus_pjc_2025.cidade.entities.Cidade;

import cidade.entities.Cidade;
import endereco.dtos.EnderecoRequest;
import endereco.dtos.EnderecoResponse;
import endereco.entities.Endereco;

public class EnderecoMapper {

    public static Endereco toEntity(EnderecoRequest request, Cidade cidade) {
        Endereco e = new Endereco();
        e.setTipoLogradouro(request.tipoLogradouro());
        e.setLogradouro(request.logradouro());
        e.setNumero(request.numero());
        e.setBairro(request.bairro());
        e.setCidade(cidade);
        return e;
    }

    public static EnderecoResponse toResponse(Endereco e) {
        return new EnderecoResponse(
                e.getId(),
                e.getTipoLogradouro(),
                e.getLogradouro(),
                e.getNumero(),
                e.getBairro(),
                e.getCidade().getId()
        );
    }

    public static void updateEntity(Endereco e, EnderecoRequest request, Cidade cidade) {
        e.setTipoLogradouro(request.tipoLogradouro());
        e.setLogradouro(request.logradouro());
        e.setNumero(request.numero());
        e.setBairro(request.bairro());
        e.setCidade(cidade);
    }
}
