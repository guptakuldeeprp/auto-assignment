package com.swiggy.autoassign.strategy.assignment;

import com.swiggy.autoassign.entity.DeliveryExec;
import com.swiggy.autoassign.delivery.DeliveryExecProvider;
import com.swiggy.autoassign.delivery.impl.InMemoryDeliveryExecProvider;
import com.swiggy.autoassign.entity.Order;
import com.swiggy.autoassign.strategy.cost.CostStrategy;
import com.swiggy.autoassign.strategy.cost.DefaultCostStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CostBasedAssignmentStrategy implements AssignmentStrategy {

    private CostStrategy costStrategy;
    private AssignmentStrategy baseStrategy; // used for tie breaking

    public CostBasedAssignmentStrategy() {
        this(new DefaultCostStrategy());
    }


    public CostBasedAssignmentStrategy(CostStrategy costStrategy) {
        this(costStrategy, null);
    }

    public CostBasedAssignmentStrategy(CostStrategy costStrategy, AssignmentStrategy baseStrategy) {
        this.baseStrategy = baseStrategy;
    }


    public DeliveryExec getDeliveryExec(Order order, DeliveryExecProvider provider) {
        if (provider == null || order == null) return null;
        Collection<DeliveryExec> deliveryExecs = provider.listDeliveryExecs();
        if (deliveryExecs.size() == 0) return null;
        double minCost = Double.MAX_VALUE;
        final List<DeliveryExec> result = new ArrayList<DeliveryExec>();
        for (DeliveryExec deliveryExec : deliveryExecs) {
            double cost = costStrategy.getCost(order, deliveryExec);

            if (cost < minCost) {
                minCost = cost;
                result.clear();
                result.add(deliveryExec);
            }
            if (cost == minCost)
                result.add(deliveryExec);
        }
        if (baseStrategy != null)
            return baseStrategy.getDeliveryExec(order, new InMemoryDeliveryExecProvider(result));
        return result.get(0);
    }

}
