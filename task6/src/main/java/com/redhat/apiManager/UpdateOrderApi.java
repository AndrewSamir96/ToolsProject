package com.redhat.apiManager;

import java.util.ArrayList;

public class UpdateOrderApi {

    private int customerId;
    private int orderId;
    ArrayList<MealApi> meals;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public ArrayList<MealApi> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<MealApi> meals) {
        this.meals = meals;
    }
}
