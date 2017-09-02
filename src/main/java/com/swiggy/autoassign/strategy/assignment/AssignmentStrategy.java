package com.swiggy.autoassign.strategy.assignment;

import com.swiggy.autoassign.entity.DeliveryExec;
import com.swiggy.autoassign.delivery.DeliveryExecProvider;
import com.swiggy.autoassign.entity.Order;

public interface AssignmentStrategy {

    public DeliveryExec getDeliveryExec(Order order, DeliveryExecProvider provider);

}
