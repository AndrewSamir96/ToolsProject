package com.redhat.rest;
import com.redhat.apiManager.*;
import com.redhat.model.Meal;
import com.redhat.model.Orders;
import com.redhat.model.Restaurant;
import com.redhat.model.User;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Set;
// role1 is the owner of the resturant
// role2 is the runner
// role3 is the customer
@RolesAllowed({"role1"})
@Stateless
@Path("/")
public class RestaurantOwnerService {
    @PersistenceContext
    private EntityManager entityManager;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("createMenu")
    public String createRestaurantMenu(MenuApi menu){
        Restaurant r = getRestaurant(menu.getRestaurantId());
        if (r != null){
            try {
                for (int i = 0; i < menu.getMeals().size(); i++){
                    if(!checkMeal(menu.getMeals().get(i).getName())){
                        r.addMeal(menu.getMeals().get(i).getName(),menu.getMeals().get(i).getPrice(),r);
                    }
                }
                for (Meal m:r.getMeals()){
                    entityManager.persist(m);
                }
                return "Meal list added successfully";
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else{
            return "User not found";
        }
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("updateMenu")
    public String updateRestaurantMenu(MenuApi menu){
        Restaurant r = getRestaurant(menu.getRestaurantId());
        if(r != null){
            try{
                for (int i = 0; i < menu.getMeals().size(); i++){
                    if(checkMeal(menu.getMeals().get(i).getName())){
                        r.updateMeal(menu.getMeals().get(i).getName(),menu.getMeals().get(i).getPrice());
                    }
                }
                for (Meal m:r.getMeals()){
                    entityManager.persist(m);
                }
                return "Meal Updated Successfully";
            }
            catch(Exception e){
                throw new RuntimeException(e);
            }
        }
        else{
            return "Meal Not Found!";
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("deleteRestaurantItem")
    public String deleteRestaurantItem(MenuApi menu){
        Restaurant r = getRestaurant(menu.getRestaurantId());
        if(r != null){
            try{
                for (int i = 0; i < menu.getMeals().size(); i++){
                    if(checkMeal(menu.getMeals().get(i).getName())){
                        Meal m = r.searchForMeal(menu.getMeals().get(i).getName());
                        entityManager.remove(m);
                    }
                }
                return "Meal Deleted Successfully";
            }
            catch(Exception e){
                throw new RuntimeException(e);
            }
        }
        else{
            return "Meal Not Found!";
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getRestaurantMenu")
    public rstMenuApi getRestaurantMenu(RestaurantIdApi r1){
        Restaurant r2 = getRestaurant(r1.getRestaurantID());
        rstMenuApi r = new rstMenuApi();
        r.setRestaurantName(r2.getName());
        for (Meal m:r2.getMeals()){
            r.getMeals().add(new MealApi(m.getName(),m.getPrice()));
        }
        return r;
    }



    ///////////////////////////////////Restaurant Functions//////////////////////////////////////


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("addRestaurant")
    public String addRestaurant(RestaurantApi r1){
        User u2 = getOwner(r1.getRestaurantOwnerId());
        u2.addRestaurant(u2,r1.getGetRestaurantName());
        Restaurant r = new Restaurant(r1.getGetRestaurantName(),u2);
        entityManager.persist(r);
        System.out.println(r.getName());
        return r.getName()+ " Restaurant added successfully with ID: " + r.getId();
    }



    public Restaurant getRestaurant(int id){
        try{
            TypedQuery<Restaurant> query = entityManager.createQuery("SELECT c from " +
                    "Restaurant c where c.id = :id",Restaurant.class);
            query.setParameter("id",id);
            return query.getSingleResult();
        }catch (Exception e){
            return null;
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("restaurantReport")
    public ReportReturnApi restaurantReport(ReportApi ra){
        User u = getOwner(ra.getOwnerId());
        Restaurant r = getRestaurant(ra.getRestaurantId());
        List<Orders>  os= getOrdersbyId(ra.getRestaurantId());
        double sum = 0;
        for (Orders o: os){
            if (o.getOrder_status().equals("delivered")){
                sum += o.getTotal_price();
            }
        }
        return new ReportReturnApi(sum,r.getTotalCompleted(),r.getTotalCanceled());
    }

    public List<Orders> getOrdersbyId(int id){
        try{
            TypedQuery<Orders> query = entityManager.createQuery("SELECT c from " +
                    "Orders c where c.restaurant.id = :id",Orders.class);
            query.setParameter("id",id);
            return query.getResultList();
        }catch (Exception e){
            return null;
        }

    }


    //////////////////////////// MEAL FUNCTIONS //////////////////////////////////
    // checking the meal in terms of the name

    public Boolean checkMeal(String meal)
    {
        try{
            TypedQuery<Meal> query = entityManager.createQuery("SELECT c from " +
                    "Meal c where c.name = :name",Meal.class);
            query.setParameter("name",meal);
            List<Meal> m = query.getResultList();
            return !m.isEmpty();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    ///////////////////////////////////Owner Functions/////////////////////////////////////


    public User getOwner(int id){
        try{

            TypedQuery<User> query = entityManager.createQuery("SELECT c from " +
                    "User c where c.id = :id",User.class);
            query.setParameter("id",id);
            return query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }


}
