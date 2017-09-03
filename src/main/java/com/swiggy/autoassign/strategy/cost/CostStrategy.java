package com.swiggy.autoassign.strategy.cost;

import com.swiggy.autoassign.entity.DeliveryExec;
import com.swiggy.autoassign.entity.Order;

public interface CostStrategy {

    double getCost(Order order, DeliveryExec deliveryExec);

}
