package com.swiggy.assign;

import com.swiggy.assign.strategy.AssignmentStrategy;
import com.swiggy.assign.delivery.DeliveryExecProvider;
import com.swiggy.assign.strategy.DefaultStrategy;

public class AutoAssignBuilder {

    private AssignmentStrategy assignmentStrategy = new DefaultStrategy();
    private DeliveryExecProvider deliveryExecProvider;

    public AutoAssignBuilder withAssignmentStrategy(AssignmentStrategy assignmentStrategy) {
        this.assignmentStrategy = assignmentStrategy;
        return this;
    }

    public AutoAssignBuilder withDeliveryExecProvider(DeliveryExecProvider deliveryExecProvider) {
        this.deliveryExecProvider = deliveryExecProvider;
        return this;
    }

    public AutoAssign build() {
        return new AutoAssign(assignmentStrategy, deliveryExecProvider);
    }

}
