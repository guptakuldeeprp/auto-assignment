package com.swiggy.assign.strategy;

import com.swiggy.delivery.DeliveryExec;
import com.swiggy.delivery.DeliveryExecProvider;
import com.swiggy.order.Order;

import java.util.List;
import java.util.Random;

public class RandomStrategy implements AssignmentStrategy {


    private final Random random = new Random();

    public DeliveryExec getDeliveryExec(Order order, DeliveryExecProvider provider) {

        if (order == null || provider == null) return null;
        List<DeliveryExec> deliveryExecs = provider.listDeliveryExecs();
        if (deliveryExecs.size() == 0) return null;
        int idx = random.nextInt(deliveryExecs.size());
        return deliveryExecs.get(idx);
    }
}
