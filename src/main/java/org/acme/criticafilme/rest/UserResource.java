package org.acme.criticafilme.rest;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.criticafilme.rest.domain.domain.User;
import org.acme.criticafilme.rest.dto.CreateUserRequest;

import jakarta.transaction.Transactional;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {


    @POST
    @Transactional
    public Response createUser(CreateUserRequest userRequest){
        User user = new User();
        user.setAge(userRequest.getAge());
        user.setName(userRequest.getName());

        user.persist();
        return  Response.ok(user).build();
    }


    @GET
    public Response listAllUsers(){
        PanacheQuery<PanacheEntityBase> query = User.findAll();
        return Response.ok(query.list()).build();
    }


    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteUser(@PathParam("id") Long id){
     User user= User.findById(id);

     if(user!= null){
         user.delete();
         return  Response.ok().build();
     }
        return  Response.status(Response.Status.NOT_FOUND).build();
    }


    @PUT
    @Path("{id}")
    @Transactional
    public Response updateUser(@PathParam("id") Long id, CreateUserRequest userData){
        User user= User.findById(id);

        if(user!= null){
            user.setAge(userData.getAge());
            user.setName(userData.getName());
            return  Response.ok(user).build();
        }
        return  Response.status(Response.Status.NOT_FOUND).build();
    }
}
