package com.redhat.apiManager;

import com.redhat.model.Meal;

import java.util.ArrayList;

public class CustomerApi {

    private String restaurantName;
    private int userID;
    ArrayList<MealApi> meals;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public ArrayList<MealApi> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<MealApi> meals) {
        this.meals = meals;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
