package com.redhat.apiManager;

import com.redhat.model.Meal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

public class ReturnOrderApi {
    private int orderId;
    private String restaurantName;
    private Set<MealApi> orderedMeals;
    private double deliveryFees;
    private String runnerName;
    private double TotalValue;
    private String timeOrdered;

    public ReturnOrderApi(String restaurantName, Set<MealApi> orderedMeals, double deliveryFees,
                          String runnerName, double totalValue, String timeOrdered) {
        this.restaurantName = restaurantName;
        this.orderedMeals = orderedMeals;
        this.deliveryFees = deliveryFees;
        this.runnerName = runnerName;
        TotalValue = totalValue;
        this.timeOrdered = timeOrdered;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Set<MealApi> getOrderedMeals() {
        return orderedMeals;
    }

    public void setOrderedMeals(Set<MealApi> orderedMeals) {
        this.orderedMeals = orderedMeals;
    }

    public double getDeliveryFees() {
        return deliveryFees;
    }

    public void setDeliveryFees(double deliveryFees) {
        this.deliveryFees = deliveryFees;
    }

    public String getRunnerName() {
        return runnerName;
    }

    public void setRunnerName(String runnerName) {
        this.runnerName = runnerName;
    }

    public double getTotalValue() {
        return TotalValue;
    }

    public void setTotalValue(double totalValue) {
        TotalValue = totalValue;
    }

    public String getTimeOrdered() {
        return timeOrdered;
    }

    public void setTimeOrdered(String timeOrdered) {
        this.timeOrdered = timeOrdered;
    }
}
