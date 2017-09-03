package com.swiggy.autoassign;

import com.swiggy.autoassign.delivery.impl.InMemoryDeliveryExecProvider;
import com.swiggy.autoassign.entity.DeliveryExec;
import com.swiggy.autoassign.entity.Order;
import com.swiggy.autoassign.strategy.assignment.CostBasedAssignmentStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Runner {

    public static void main(String[] args) {
        List<DeliveryExec> deliveryExecs = new ArrayList<>();

        // add dummy DeliveryExec(s) here

        List<Order> orders = new ArrayList<>();
        // add dummy Order(s) here

        AutoAssign autoAssign = AutoAssign.builder().withAssignmentStrategy(new CostBasedAssignmentStrategy()).withDeliveryExecProvider(new InMemoryDeliveryExecProvider(deliveryExecs)).build();
        Map<Order, DeliveryExec> assingment = autoAssign.assing(orders);

        System.out.println("assingment: " + assingment);
    }
}
