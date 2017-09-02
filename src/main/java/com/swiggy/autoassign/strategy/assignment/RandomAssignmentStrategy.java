package com.swiggy.autoassign.strategy.assignment;

import com.swiggy.autoassign.entity.DeliveryExec;
import com.swiggy.autoassign.delivery.DeliveryExecProvider;
import com.swiggy.autoassign.entity.Order;

import java.util.Collection;
import java.util.Random;

public class RandomAssignmentStrategy implements AssignmentStrategy {


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
