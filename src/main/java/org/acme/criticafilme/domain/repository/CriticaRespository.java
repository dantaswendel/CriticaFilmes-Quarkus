package org.acme.criticafilme.domain.repository;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.criticafilme.domain.domain.Critica;

@ApplicationScoped
public class CriticaRespository implements PanacheRepository<Critica> {



}
