package com.redhat.rest;

import com.redhat.apiManager.*;
import com.redhat.model.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.*;
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
@RolesAllowed({"role3"})
@Stateless
@Path("/")
public class CustomerService {
    //TO DO: create Restaurant Report
    //security and authentication
    @PersistenceContext
    private EntityManager entityManager;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("createOrder")
    public ReturnOrderApi createOrder(CustomerApi c){
        ///////////////////////PREPARING ORDER RECEIPT///////////////////////
        Restaurant r = getRestaurantByName(c.getRestaurantName());
        //Customer that ordered
        User u = getCustomer(c.getUserID());
        //Restaurant Name
        String restaurantN = c.getRestaurantName();
        //list of meals
        Set<Meal> orderItems = new HashSet<>();
        System.out.println(c.getMeals().size());
        for(int i = 0; i<c.getMeals().size();i++){
            Meal m = r.searchForMeal(c.getMeals().get(i).getName());
            if(m==null){
                return null;
            }
            orderItems.add(m);
        }
        //random available runner name and fees
        Random rand = new Random();
        List<Runner> avRunner = getRandomRunners();
        if (avRunner == null)
        {
            return null;
        }
        Runner randomRunner = avRunner.get(rand.nextInt(avRunner.size()));
        //Runner name
        String runnerName = randomRunner.getName();
        //Runner Fees
        double runnerFees = randomRunner.getDelivery_fees();
        //sum of meals
        double sumOfMeals=0;
        for (Meal m:orderItems){
            sumOfMeals += m.getPrice();
        }
        double totalValue = sumOfMeals + randomRunner.getDelivery_fees();
        ////date of order
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String timeOrdered = dtf.format(now);
        ///////////////////////PREPARING ORDER RECEIPT///////////////////////
        //Order
        Orders o = new Orders(totalValue,randomRunner,r,u,"preparing");
        for (Meal m : orderItems){
            m.getOrders().add(o);
        }
        o.setMeals(orderItems);
        //Runner
        randomRunner.setRunner_status("busy");
        randomRunner.setOrders(o);
        //User
        entityManager.persist(o);
        u.addOrder(o);
        r.addOrder(o);
        entityManager.persist(randomRunner);
        entityManager.persist(u);
        entityManager.persist(r);
        Set<MealApi> orderItems2 = new HashSet<>();
        for(Meal m17: orderItems){
            orderItems2.add(new MealApi(m17.getName(),m17.getPrice()));
        }
        ReturnOrderApi returner = new ReturnOrderApi(restaurantN,orderItems2,runnerFees,runnerName,totalValue,timeOrdered);
        returner.setOrderId(o.getId());
        return returner;
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("cancelOrder")
    public String cancelOrder(cancelApi ca){
        User u = getCustomer(ca.getCustomerId());
        for (Orders o: u.getOrders()){
            if (o.getId()==ca.getOrderId() && o.getOrder_status().equals("preparing")){
                o.setOrder_status("canceled");
                o.getRestaurant().incrementCd();
                entityManager.persist(o);
                return "Order Canceled Successfully!";
            }
        }
        return "Order not found!";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("updateOrder")
    public String updateOrder(UpdateOrderApi uoi){
        double sum = 0;
        User u = getCustomer(uoi.getCustomerId());
        for (Orders o: u.getOrders()) {
            if (o.getId() == uoi.getOrderId() && o.getOrder_status().equals("preparing")) {
                for (Meal m : o.getMeals()){
                    m.getOrders().remove(o);
                }
                Set<Meal> setItems = new HashSet<>();
                for(int i = 0; i<uoi.getMeals().size();i++){
                    Meal m = o.getRestaurant().searchForMeal(uoi.getMeals().get(i).getName());
                    if(m==null){
                        return "Meal not found!";
                    }
                    sum += m.getPrice();
                    setItems.add(m);
                }
                for (Meal m : setItems){
                    m.getOrders().add(o);
                }

                double totalValue = sum + o.getRunner().getDelivery_fees();
                o.setTotal_price(totalValue);
                entityManager.persist(o);
                return "Order updated Successfully!";
            }
        }
        return "Order not found!";
    }

    public Restaurant getRestaurantByName(String name){
        try{
            TypedQuery<Restaurant> query = entityManager.createQuery("SELECT c from " +
                    "Restaurant c where c.name = :name",Restaurant.class);
            query.setParameter("name",name);
            return query.getSingleResult();
        }catch (Exception e){
            return null;
        }

    }


    public List<Runner> getRandomRunners(){
        try{
            String av = "available";
            TypedQuery<Runner> query = entityManager.createQuery("SELECT c from " +
                    "Runner c where c.runner_status = :runner_status",Runner.class);
            query.setParameter("runner_status",av);
            return query.getResultList();
        }catch (Exception e){
            return null;
        }

    }


    public User getCustomer(int id){
        try{
            TypedQuery<User> query = entityManager.createQuery("SELECT c from " +
                    "User c where c.id = :id",User.class);
            query.setParameter("id",id);
            return query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getRestaurantNames")
    public List<String> getRestaurantNames(OnlyUserIdApi k){
        try{
            TypedQuery<Restaurant> query = entityManager.createQuery("SELECT c from " +
                    "Restaurant c",Restaurant.class);
            List<Restaurant> r =  query.getResultList();
            List<String> rNames = new ArrayList<>();
            for(Restaurant rn: r){
                rNames.add(rn.getName());
            }
            return rNames;
        }catch (Exception e){
            return null;
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listOrdersById")
    public List<PrettyOrderApi> listOrdersById(OnlyUserIdApi oi){
        int id = oi.getOUID();
        TypedQuery<Orders> query = entityManager.createQuery("SELECT c from Orders c "
                ,Orders.class);
        List<Orders> o = query.getResultList();
        List<PrettyOrderApi> o3 = new ArrayList<>(o.size());
        for(int i = 0; i < o.size(); i++){
            if(o.get(i).getUser().getId()==id){
                o3.add(new PrettyOrderApi(o.get(i).getId(),o.get(i).getTotal_price(),o.get(i).getRunner().getName(),o.get(i).getRunner().getDelivery_fees(),o.get(i).getOrder_status()));
                for (Meal k: o.get(i).getMeals()){
                    o3.get(i).putMeal(k.getName(), k.getPrice());
                }
            }
        }
        return o3;
    }
}
