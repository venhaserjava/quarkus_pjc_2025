package pessoa.repositories;
//package com.rossatti.quarkus_pjc_2025.pessoa.repositories;
//import com.rossatti.quarkus_pjc_2025.pessoa.entities.Pessoa;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import pessoa.entities.Pessoa;

@ApplicationScoped
public class PessoaRepository implements PanacheRepository<Pessoa> {
}
