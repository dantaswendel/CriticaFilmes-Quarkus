package org.acme.criticafilme.domain.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.criticafilme.domain.domain.Follower;


@ApplicationScoped
public class FollowerRepository implements PanacheRepository<Follower> {
}
