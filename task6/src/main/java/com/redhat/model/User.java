package com.redhat.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY )
    private int id;

    private String name;

    private String role;
    @OneToMany(mappedBy = "user")
    private Set<Restaurant> restaurant;

    @OneToMany(mappedBy = "user")
    private Set<Orders> orders;

    public User() {
    }
    public User(String name, String role) {
        this.name = name;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String addRestaurant(User userName, String restaurantName){
        restaurant.add(new Restaurant(restaurantName,userName));
        return "Meal added!";
    }

    public Set<Restaurant> getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Set<Restaurant> restaurant) {
        this.restaurant = restaurant;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }
    public String addOrder(Orders o){
        orders.add(o);
        return "Order added";
    }
}
