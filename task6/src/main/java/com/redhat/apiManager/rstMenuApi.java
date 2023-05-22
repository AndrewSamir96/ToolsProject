package com.redhat.apiManager;

import com.redhat.rest.Service;

import java.util.HashSet;
import java.util.Set;

public class rstMenuApi {

    private String restaurantName;
    private Set<MealApi> meals;

    public rstMenuApi() {
        this.meals = new HashSet<>();
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Set<MealApi> getMeals() {
        return meals;
    }

    public void setMeals(Set<MealApi> meals) {
        this.meals = meals;
    }
}
