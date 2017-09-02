package com.swiggy.assign.strategy;

import com.swiggy.assign.entity.DeliveryExec;
import com.swiggy.assign.delivery.DeliveryExecProvider;
import com.swiggy.assign.delivery.impl.InMemoryDeliveryExecProvider;
import com.swiggy.assign.entity.Order;
import com.swiggy.util.Haversine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DefaultStrategy implements AssignmentStrategy {

    private AssignmentStrategy baseStrategy; // used for tie breaking

    public DefaultStrategy() {

    }

    public DefaultStrategy(AssignmentStrategy baseStrategy) {
        this.baseStrategy = baseStrategy;
    }


    public DeliveryExec getDeliveryExec(Order order, DeliveryExecProvider provider) {
        if (provider == null || order == null) return null;
        Collection<DeliveryExec> deliveryExecs = provider.listDeliveryExecs();
        if (deliveryExecs.size() == 0) return null;
        double minCost = Double.MAX_VALUE;
        final List<DeliveryExec> result = new ArrayList<DeliveryExec>();
        for (DeliveryExec deliveryExec : deliveryExecs) {
            double cost = cost(order, deliveryExec);

            if (cost < minCost) {
                minCost = cost;
                result.clear();
                result.add(deliveryExec);
            }
            if (cost == minCost)
                result.add(deliveryExec);
        }
        if (baseStrategy != null)
            return baseStrategy.getDeliveryExec(order, new InMemoryDeliveryExecProvider(result));
        return result.get(0);
    }


    protected double cost(Order order, DeliveryExec deliveryExec) {
        double haversine = Haversine.distance(deliveryExec.getLat(), deliveryExec.getLon(), order.getLat(), order.getLon());
        return haversine * (1 / order.getOrderTime()) * (1 / deliveryExec.getLastOrderDeliveryTime());
    }
}
