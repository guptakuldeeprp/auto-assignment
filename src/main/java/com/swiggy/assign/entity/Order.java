package com.swiggy.assign.entity;

import java.util.concurrent.TimeUnit;

public class Order {

    long id;
    double lat;
    double lon;
    double orderTime = TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis());

    public Order() {

    }

    public Order(long id, double lat, double lon, double orderTime) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.orderTime = orderTime;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(double orderTime) {
        this.orderTime = orderTime;
    }
}
