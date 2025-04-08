package unidade.repositories;
//package com.rossatti.quarkus_pjc_2025.unidade.repositories;

//import com.rossatti.quarkus_pjc_2025.unidade.entities.Unidade;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unidade.entities.Unidade;

@ApplicationScoped
public class UnidadeRepository implements PanacheRepository<Unidade> {
}
