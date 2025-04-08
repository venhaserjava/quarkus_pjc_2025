package pessoa.mappers;
//package com.rossatti.quarkus_pjc_2025.pessoa.mappers;

//import com.rossatti.quarkus_pjc_2025.pessoa.dtos.PessoaRequest;
//import com.rossatti.quarkus_pjc_2025.pessoa.dtos.PessoaResponse;
//import com.rossatti.quarkus_pjc_2025.pessoa.entities.Pessoa;

import pessoa.dtos.PessoaRequest;
import pessoa.dtos.PessoaResponse;
import pessoa.entities.Pessoa;

public class PessoaMapper {

    public static Pessoa toEntity(PessoaRequest request) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(request.nome());
        pessoa.setMae(request.mae());
        pessoa.setPai(request.pai());
        pessoa.setSexo(request.sexo());
        pessoa.setDataNascimento(request.dataNascimento());
        return pessoa;
    }

    public static PessoaResponse toResponse(Pessoa pessoa) {
        return new PessoaResponse(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getMae(),
                pessoa.getPai(),
                pessoa.getSexo(),
                pessoa.getDataNascimento()
        );
    }

    public static void updateEntity(Pessoa pessoa, PessoaRequest request) {
        pessoa.setNome(request.nome());
        pessoa.setMae(request.mae());
        pessoa.setPai(request.pai());
        pessoa.setSexo(request.sexo());
        pessoa.setDataNascimento(request.dataNascimento());
    }
}
