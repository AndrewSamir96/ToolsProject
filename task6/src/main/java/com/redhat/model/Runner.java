package com.redhat.model;

import javax.persistence.*;

@Entity
public class Runner {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY )
    private int id;

    private String name;

    //status(available, busy)
    private String runner_status;
    private int deliveriesDone;

    private double delivery_fees;
    @OneToOne
    @JoinColumn(name="runner_id")
    private Orders orders;

    public Runner(String name, String runner_status) {
        this.name = name;
        this.runner_status = runner_status;
        this.deliveriesDone = 0;
    }

    public Runner() {
        this.deliveriesDone = 0;
    }

    public void incrementD(){
        deliveriesDone++;
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

    public String getRunner_status() {
        return runner_status;
    }

    public void setRunner_status(String runner_status) {
        this.runner_status = runner_status;
    }

    public double getDelivery_fees() {
        return delivery_fees;
    }

    public void setDelivery_fees(double delivery_fees) {
        this.delivery_fees = delivery_fees;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public int getDeliveriesDone() {
        return deliveriesDone;
    }
}
