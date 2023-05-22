package com.redhat.apiManager;

public class ReportReturnApi {
    private double totalProfit;
    private int completedOrders;
    private int canceledOrders;

    public ReportReturnApi(double totalProfit, int completedOrders, int canceledOrders) {
        this.totalProfit = totalProfit;
        this.completedOrders = completedOrders;
        this.canceledOrders = canceledOrders;
    }

    public ReportReturnApi() {
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public int getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(int completedOrders) {
        this.completedOrders = completedOrders;
    }

    public int getCanceledOrders() {
        return canceledOrders;
    }

    public void setCanceledOrders(int canceledOrders) {
        this.canceledOrders = canceledOrders;
    }
}
