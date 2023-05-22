package com.redhat.rest;

import com.redhat.apiManager.OnlyUserIdApi;
import com.redhat.apiManager.RunnerApi;
import com.redhat.model.Runner;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
// role1 is the owner of the resturant
// role2 is the runner
// role3 is the customer
@RolesAllowed({"role2"})
@Stateless
@Path("/")
public class RunnerService {

    @PersistenceContext
    private EntityManager entityManager;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("setFees")
    public String setFees(RunnerApi r){
        Runner  run = getRunner(r.getId());
        run.setDelivery_fees(r.getDelivery_fees());
        entityManager.persist(run);
        return "Fees changed to "+ r.getDelivery_fees();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("deliverOrder")
    public String deliverOrder(OnlyUserIdApi iu){
        Runner r = getRunner(iu.getOUID());
        if(r.getOrders().getOrder_status().equals("preparing")){
            r.setRunner_status("available");
            r.incrementD();
            r.getOrders().setOrder_status("delivered");
            r.getOrders().getRestaurant().incrementC();
            entityManager.persist(r);
            return "Order delivered successfully! ";
        }
        return "Order is canceled or delivered";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("checkCompleted")
    public String checkCompleted(OnlyUserIdApi iu){
        Runner r = getRunner(iu.getOUID());
        return "Number of orders Completed: " + r.getDeliveriesDone();
    }
    public Runner getRunner(int id){
        try{

            TypedQuery<Runner> query = entityManager.createQuery("SELECT c from " +
                    "Runner c where c.id = :id",Runner.class);
            query.setParameter("id",id);
            return query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

////////////////////////////////randomRunner.setOrders(o);
}
