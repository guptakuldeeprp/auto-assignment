package com.swiggy.assign.strategy;

import com.swiggy.assign.entity.DeliveryExec;
import com.swiggy.assign.delivery.DeliveryExecProvider;
import com.swiggy.assign.entity.Order;

public interface AssignmentStrategy {

    public DeliveryExec getDeliveryExec(Order order, DeliveryExecProvider provider);

}
