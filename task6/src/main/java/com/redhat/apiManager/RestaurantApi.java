package com.redhat.apiManager;

public class RestaurantApi {

    private int restaurantOwnerId;
    private  String getRestaurantName;

    public int getRestaurantOwnerId() {
        return restaurantOwnerId;
    }

    public void setRestaurantOwnerId(int restaurantOwnerId) {
        this.restaurantOwnerId = restaurantOwnerId;
    }

    public String getGetRestaurantName() {
        return getRestaurantName;
    }

    public void setGetRestaurantName(String getRestaurantName) {
        this.getRestaurantName = getRestaurantName;
    }
}
