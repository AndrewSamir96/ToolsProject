package com.redhat.apiManager;

import java.util.ArrayList;

public class MenuApi {

    private int ownerId;
    private int restaurantId;

    ArrayList<MealApi> meals;

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public ArrayList<MealApi> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<MealApi> meals) {
        this.meals = meals;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
