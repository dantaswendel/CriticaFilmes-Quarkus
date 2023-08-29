package org.acme.criticafilme.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.criticafilme.domain.domain.User;
import org.acme.criticafilme.domain.repository.FollowerRepository;
import org.acme.criticafilme.domain.repository.UserRepository;

@Path("/users/{uderId}/followers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FollowerResource {


    private FollowerRepository repository;
    private UserRepository userRepository;


    @Inject
    public FollowerResource(FollowerRepository repository, UserRepository userRepository){

        this.repository = repository;
        this.userRepository = userRepository;
    }


}
