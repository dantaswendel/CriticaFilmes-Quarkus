package org.acme.criticafilme.rest;


import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.criticafilme.domain.domain.Critica;
import org.acme.criticafilme.domain.domain.User;
import org.acme.criticafilme.domain.repository.CriticaRespository;
import org.acme.criticafilme.domain.repository.UserRepository;
import org.acme.criticafilme.rest.dto.CreateCriticaRequest;

import java.time.LocalDateTime;

@Path("/criticas/{user_id}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CriticaResource {

    private UserRepository userRepository;
    private CriticaRespository criticaRespository;


    @Inject
    public CriticaResource(UserRepository userRepository, CriticaRespository criticaRespository) {
        this.userRepository = userRepository;
        this.criticaRespository = criticaRespository;
    }

    @POST
    @Transactional
    public Response saveCritica(@PathParam("user_id") Long user_id, CreateCriticaRequest request){

        User user =userRepository.findById(user_id);

        if(user==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Critica critica = new Critica();
        critica.setFilme(request.getFilme());
        critica.setCritica_text(request.getCritica_text());
        critica.setDateTime(LocalDateTime.now());
        critica.setUser(user);

        criticaRespository.persist(critica);

        return  Response.status(Response.Status.CREATED).build();
    }


    @GET
    public Response listCriticas(@PathParam("userId") Long userId){

        return  Response.ok().build();
    }

}
