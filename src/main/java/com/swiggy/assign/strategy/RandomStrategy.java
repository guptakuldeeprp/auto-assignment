package com.swiggy.assign.strategy;

import com.swiggy.assign.entity.DeliveryExec;
import com.swiggy.assign.delivery.DeliveryExecProvider;
import com.swiggy.assign.entity.Order;

import java.util.Collection;
import java.util.Random;

public class RandomStrategy implements AssignmentStrategy {


    private final Random random = new Random();

    public DeliveryExec getDeliveryExec(Order order, DeliveryExecProvider provider) {

        if (order == null || provider == null) return null;
        Collection<DeliveryExec> deliveryExecs = provider.listDeliveryExecs();
        if (deliveryExecs.size() == 0) return null;
        return deliveryExecs.stream()
                .skip((int) (deliveryExecs.size() * Math.random()))
                .findFirst().get();

//        int idx = random.nextInt(deliveryExecs.size());
//        return deliveryExecs.get(idx);

    }
}
