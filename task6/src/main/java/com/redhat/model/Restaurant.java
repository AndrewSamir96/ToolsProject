package com.redhat.model;
import javax.persistence.*;
import java.util.Set;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY )
    private int id;

    private String name;

    private int totalCompleted;
    private int totalCanceled;

    @OneToMany(mappedBy = "restaurant", fetch=FetchType.EAGER)
    private Set<Meal> meals;

    @OneToMany(mappedBy = "restaurant", fetch=FetchType.EAGER)
    private Set<Orders> orders;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Restaurant(String name, User user) {
        this.name = name;
        this.user = user;
        totalCompleted = 0;
        totalCanceled = 0;
    }

    public Restaurant() {
        totalCompleted = 0;
        totalCanceled = 0;
    }

    public void incrementC(){
        totalCompleted++;
    }
    public void incrementCd(){
        totalCanceled++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Meal> getMeals() {
        return meals;
    }

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }

    public String addMeal(String mealName, double mealPrice,Restaurant r){
        meals.add(new Meal(mealName,mealPrice,r));
        return "Meal added!";
    }

    public Meal searchForMeal(String mealName)
    {
        for(Meal m:meals)
        {
            if(m.getName().equals(mealName))
            {
                return m;
            }
        }
        return null;
    }

    public String updateMeal(String mealName, double mealPrice)
    {
        Meal k = searchForMeal(mealName);
        k.setName(mealName);
        k.setPrice(mealPrice);
        return "Meal updated";
    }

    public void addOrder(Orders o){
        orders.add(o);
    }

    public int getTotalCompleted() {
        return totalCompleted;
    }

    public int getTotalCanceled() {
        return totalCanceled;
    }
}
