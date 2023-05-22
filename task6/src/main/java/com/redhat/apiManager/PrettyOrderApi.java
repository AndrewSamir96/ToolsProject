package com.redhat.apiManager;

import com.redhat.model.Runner;

import java.util.HashSet;
import java.util.Set;

public class PrettyOrderApi {
    private int id;
    private Set<MealApi> meals = new HashSet<>();
    private String  runnerName;
    private String orderStatus;
    private double runnerFees;
    private double totalPrice;


    public void putMeal(String name,double price){
        meals.add(new MealApi(name,price));
    }
    public int getId() {
        return id;
    }

    public PrettyOrderApi(int id, double totalPrice, String runnerName, double runnerFees, String os) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.runnerName = runnerName;
        this.runnerFees = runnerFees;
        this.orderStatus = os;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<MealApi> getMeals() {
        return meals;
    }

    public void setMeals(Set<MealApi> meals) {
        this.meals = meals;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getRunnerName() {
        return runnerName;
    }

    public void setRunnerName(String runnerName) {
        this.runnerName = runnerName;
    }

    public double getRunnerFees() {
        return runnerFees;
    }

    public void setRunnerFees(double runnerFees) {
        this.runnerFees = runnerFees;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
