package com.redhat.rest;

import com.redhat.apiManager.UserApi;
import com.redhat.model.Runner;
import com.redhat.model.User;

import javax.annotation.security.PermitAll;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// role1 is the owner of the resturant
// role2 is the runner
// role3 is the customer
@RolesAllowed({"role1","role2","role3"})
@Stateless
@Path("/")
public class UserService {
    ///////////////////////////// USER FUNCTIONS //////////////////////////////////
    @PersistenceContext
    private EntityManager entityManager;

    @PermitAll
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("addUser")
    public String addUser(UserApi u1){
        User user = new User(u1.getName(),u1.getRole());
        if(u1.getRole().equals("Runner")){
            Runner run = new Runner(u1.getName(),"available");
            entityManager.persist(run);
            entityManager.persist(user);
            return user.getRole()+" added successfully: " + user.getName() + " with ID: "+user.getId()
                    +" and runner ID: " +run.getId();
        }
        else{
            entityManager.persist(user);
            return user.getRole()+" added successfully: " + user.getName() + " with ID: "+user.getId();
        }



    }

}
