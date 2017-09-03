package com.swiggy.autoassign.strategy.cost;

import com.swiggy.autoassign.entity.DeliveryExec;
import com.swiggy.autoassign.entity.Order;
import com.swiggy.util.Haversine;

public class DefaultCostStrategy implements CostStrategy {
    @Override
    public double getCost(Order order, DeliveryExec deliveryExec) {
        double haversine = Haversine.distance(deliveryExec.getLat(), deliveryExec.getLon(), order.getLat(), order.getLon());
        return haversine * (1D / order.getOrderTime()) * (1D / deliveryExec.getLastOrderDeliveryTime());
    }
}
