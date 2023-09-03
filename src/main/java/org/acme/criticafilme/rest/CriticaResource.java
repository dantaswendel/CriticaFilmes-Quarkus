package org.acme.criticafilme.rest;


import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.criticafilme.domain.domain.Critica;
import org.acme.criticafilme.domain.domain.User;
import org.acme.criticafilme.domain.repository.CriticaRespository;
import org.acme.criticafilme.domain.repository.FollowerRepository;
import org.acme.criticafilme.domain.repository.UserRepository;
import org.acme.criticafilme.rest.dto.CreateCriticaRequest;
import org.acme.criticafilme.rest.dto.CriticaResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Path("/criticas/{user_id}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CriticaResource {

    private UserRepository userRepository;
    private CriticaRespository criticaRespository;

    private FollowerRepository followerRepository;


    @Inject
    public CriticaResource(UserRepository userRepository, CriticaRespository criticaRespository, FollowerRepository followerRepository) {
        this.userRepository = userRepository;
        this.criticaRespository = criticaRespository;
        this.followerRepository = followerRepository;

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
    public Response listCriticas(@PathParam("user_id") Long user_id,
                                 @HeaderParam("followerId") Long followerId){

        User user =userRepository.findById(user_id);
        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if(followerId == null){
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("You forgot the header followerId")
                    .build();
        }

        User follower = userRepository.findById(followerId);

        if(follower == null){
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Inexistent followerId")
                    .build();
        }

        boolean follows = followerRepository.follows(follower, user);

        if (!follows){

           return  Response.status(Response.Status.FORBIDDEN).entity("Voce nao pode ver as criticas desse usuario").build();
       }


        PanacheQuery<Critica> query = criticaRespository.find("user", Sort.by("dateTime", Sort.Direction.Descending),user);

        List<Critica> list = query.list();

      var criticaResponseList  = list.stream()
              .map(critica -> CriticaResponse.fromEntity(critica))
              .collect(Collectors.toList());

        return  Response.ok(criticaResponseList).build();
    }

}
