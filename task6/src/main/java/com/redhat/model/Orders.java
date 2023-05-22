package com.redhat.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY )
    private int id;

    @ManyToMany(mappedBy = "orders", fetch=FetchType.EAGER)
    private Set<Meal> meals;

    private double total_price;
    @OneToOne(mappedBy = "orders")
    private Runner runner;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //order_status(preparing, delivered,canceled )
    private String order_status;

    public Orders(double total_price, Runner runner,
                  Restaurant restaurant, User user, String order_status) {
        this.total_price = total_price;
        this.runner = runner;
        this.restaurant = restaurant;
        this.user = user;
        this.order_status = order_status;
    }

    public Orders() {
    }

    public void addMeals(Meal m){
        m = new Meal();
        meals.add(m);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Meal> getMeals() {
        return meals;
    }

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
}
