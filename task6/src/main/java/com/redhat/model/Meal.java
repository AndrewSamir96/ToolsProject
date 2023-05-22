package com.redhat.model;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY )
    private int id;

    private String name;

    private double  price;
    //REMOVE ORDER RELATION WITH MEALS
    @ManyToMany
    @JoinColumn(name = "orders_id")
    private Set<Orders> orders;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;


    public Meal(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Meal(String name, double price, Restaurant restaurant) {
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }

    public Meal() {}

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Orders> getOrders() {
        return orders;
    }
    /*public String addOrder(String mealName, double mealPrice,Restaurant r){
        meals.add(new Meal(mealName,mealPrice,r));
        return "Meal added!";
    }*/

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
