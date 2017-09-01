package com.swiggy.assign.strategy;

import com.swiggy.delivery.DeliveryExec;
import com.swiggy.delivery.DeliveryExecProvider;
import com.swiggy.delivery.impl.InMemoryDeliveryExecProvider;
import com.swiggy.order.Order;
import com.swiggy.util.Haversine;

import java.util.ArrayList;
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
        List<DeliveryExec> deliveryExecs = provider.listDeliveryExecs();
        if (deliveryExecs.size() == 0) return null;
        double minScore = Double.MAX_VALUE;
        final List<DeliveryExec> result = new ArrayList<DeliveryExec>();
        for (DeliveryExec deliveryExec : deliveryExecs) {
            double score = score(order, deliveryExec);

            if (score < minScore) {
                minScore = score;
                result.clear();
                result.add(deliveryExec);
            }
            if (score == minScore)
                result.add(deliveryExec);
        }
        if (baseStrategy != null)
            return baseStrategy.getDeliveryExec(order, new InMemoryDeliveryExecProvider(result));
        return result.get(0);
    }


    protected double score(Order order, DeliveryExec deliveryExec) {
        double haversine = Haversine.distance(deliveryExec.getLat(), deliveryExec.getLon(), order.getLat(), order.getLon());
        return haversine * (1 / order.getOrderTime()) * (1 / deliveryExec.getLastOrderDeliveryTime());
    }
}
