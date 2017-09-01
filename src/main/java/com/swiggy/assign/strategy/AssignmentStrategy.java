package com.swiggy.assign.strategy;

import com.swiggy.delivery.DeliveryExec;
import com.swiggy.delivery.DeliveryExecProvider;
import com.swiggy.order.Order;

public interface AssignmentStrategy {

    public DeliveryExec getDeliveryExec(Order order, DeliveryExecProvider provider);

}
