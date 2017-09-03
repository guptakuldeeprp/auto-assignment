package com.swiggy.autoassign;

import com.swiggy.autoassign.delivery.impl.InMemoryDeliveryExecProvider;
import com.swiggy.autoassign.entity.DeliveryExec;
import com.swiggy.autoassign.entity.Order;
import com.swiggy.autoassign.strategy.assignment.CostBasedAssignmentStrategy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class Runner {

    public static void main(String[] args) {
        List<DeliveryExec> deliveryExecs = new ArrayList<>();
        DeliveryExec de0 = new DeliveryExec(0, 19.218331, 72.97809, 150, 1);
        DeliveryExec de1 = new DeliveryExec(1, 19.214904, 72.977843, 130, 1);
        DeliveryExec de2 = new DeliveryExec(2, 19.221226, 72.989388, 170, 1);
        DeliveryExec de3 = new DeliveryExec(3, 19.239817, 72.991619, 100, 1);
        deliveryExecs.add(de0);
        deliveryExecs.add(de1);
        deliveryExecs.add(de2);
        deliveryExecs.add(de3);

        // add dummy DeliveryExec(s) here

        List<Order> orders = new ArrayList<>();
        Order order = new Order(0, 19.27386, 72.989902, 100);
        Order order1 = new Order(1, 19.27386, 72.989902, 120);
        orders.add(order);
        orders.add(order1);
        // add dummy Order(s) here

        AutoAssign autoAssign = AutoAssign.builder().withAssignmentStrategy(new CostBasedAssignmentStrategy()).withDeliveryExecProvider(new InMemoryDeliveryExecProvider(deliveryExecs)).build();
        Map<Order, DeliveryExec> assingment = autoAssign.assing(orders);

        System.out.println("assingment: " + assingment);
    }
}

