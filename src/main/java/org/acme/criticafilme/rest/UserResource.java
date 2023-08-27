package org.acme.criticafilme.rest;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.criticafilme.domain.domain.User;
import org.acme.criticafilme.domain.repository.UserRepository;
import org.acme.criticafilme.rest.dto.CreateUserRequest;
import jakarta.transaction.Transactional;
import org.acme.criticafilme.rest.dto.ResponseErrors;

import java.util.Set;


@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {


    private UserRepository repository;
    private Validator validator;

    @Inject
    public UserResource(UserRepository repository, Validator validator){
        this.repository = repository;
        this.validator = validator;
    }

    @POST
    @Transactional
    public Response createUser(CreateUserRequest userRequest){


        Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(userRequest);
        if (!violations.isEmpty()){

            return ResponseErrors.createFormValidation(violations).withStatusCode(ResponseErrors.UNPROCESSABLE_ENTITY_STATUS);
        }
        User user = new User();
        user.setAge(userRequest.getAge());
        user.setName(userRequest.getName());

        repository.persist(user);
        return  Response.status(Response.Status.CREATED.getStatusCode())
                .entity(user)
                .build();
    }


    @GET
    public Response listAllUsers(){
        PanacheQuery<User> query = repository.findAll();
        return Response.ok(query.list()).build();
    }


    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteUser(@PathParam("id") Long id){
     User user= repository.findById(id);

     if(user!= null){
         repository.delete(user);
         return  Response.noContent().build();
     }
        return  Response.status(Response.Status.NOT_FOUND).build();
    }


    @PUT
    @Path("{id}")
    @Transactional
    public Response updateUser(@PathParam("id") Long id, CreateUserRequest userData){
        User user= repository.findById(id);

        if(user!= null){
            user.setAge(userData.getAge());
            user.setName(userData.getName());
            return  Response.noContent().build();
        }
        return  Response.status(Response.Status.NOT_FOUND).build();
    }
}
