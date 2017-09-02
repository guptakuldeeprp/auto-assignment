package com.swiggy.assign.entity;

public class DeliveryExec {

    long id;
    long lat;
    long lon;
    long lastOrderDeliveryTime; // in minutes
    long numOrdersDelivered;

    public DeliveryExec() {

    }

    public DeliveryExec(long id, long lat, long lon, long lastOrderDeliveryTime, long numOrdersDelivered) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.lastOrderDeliveryTime = lastOrderDeliveryTime;
        this.numOrdersDelivered = numOrdersDelivered;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLat() {
        return lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public long getLon() {
        return lon;
    }

    public void setLon(long lon) {
        this.lon = lon;
    }

    public long getLastOrderDeliveryTime() {
        return lastOrderDeliveryTime;
    }

    public void setLastOrderDeliveryTime(long lastOrderDeliveryTime) {
        this.lastOrderDeliveryTime = lastOrderDeliveryTime;
    }

    public long getNumOrdersDelivered() {
        return numOrdersDelivered;
    }

    public void setNumOrdersDelivered(long numOrdersDelivered) {
        this.numOrdersDelivered = numOrdersDelivered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliveryExec that = (DeliveryExec) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
