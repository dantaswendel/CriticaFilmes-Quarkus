package org.acme.criticafilme.rest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.criticafilme.domain.domain.Follower;
import org.acme.criticafilme.domain.domain.User;
import org.acme.criticafilme.domain.repository.FollowerRepository;
import org.acme.criticafilme.domain.repository.UserRepository;
import org.acme.criticafilme.rest.dto.FollowerRequest;
import org.acme.criticafilme.rest.dto.FollowerResponse;
import org.acme.criticafilme.rest.dto.FollowersPerUserResponse;

import java.util.List;
import java.util.stream.Collectors;

@Path("/followers/{userId}")
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


    @PUT
    @Transactional
    public Response followUser(@PathParam("userId")Long userId, FollowerRequest request){

    if(userId.equals(request.getFollowerId())){

        return Response.status(Response.Status.CONFLICT)
            .entity("Voce não pode seguir a si mesmo")
            .build();
      }

        var user = userRepository.findById(userId);

        if (user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        var follower = userRepository.findById(request.getFollowerId());
        boolean follows = repository.follows(follower, user);

        if (!follows){

            var entity = new Follower();
            entity.setUser(user);
            entity.setFollower(follower);

            repository.persist(entity);

        }

    return Response.status(Response.Status.NO_CONTENT).build();

    }


    @GET
    public Response listFOllowers(@PathParam("userId") Long userId){

        var user = userRepository.findById(userId);
        if (user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        var list = repository.findByUser(userId);
        FollowersPerUserResponse responseObject = new FollowersPerUserResponse();
        responseObject.setFollowersaCount(list.size());

       var followerList = list.stream().map(FollowerResponse::new).collect(Collectors.toList());

        responseObject.setContent(followerList);
        return Response.ok(responseObject).build();


    }


    @DELETE
    @Transactional
    public Response unfollowUser(@PathParam("userId") Long userId,
                                 @QueryParam("followerId") Long followerId){

        User user= userRepository.findById(userId);

        if(user== null){
            return  Response.status(Response.Status.NOT_FOUND).build();
        }

        repository.deleteByFollowerAndUser(followerId,userId);
        return  Response.status(Response.Status.NO_CONTENT).build();
    }

}
