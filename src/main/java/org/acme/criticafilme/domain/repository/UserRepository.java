package org.acme.criticafilme.domain.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.criticafilme.domain.domain.User;


@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {


}
