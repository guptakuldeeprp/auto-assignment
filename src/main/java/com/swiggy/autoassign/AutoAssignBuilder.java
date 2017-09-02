package com.swiggy.autoassign;

import com.swiggy.autoassign.strategy.assignment.AssignmentStrategy;
import com.swiggy.autoassign.delivery.DeliveryExecProvider;
import com.swiggy.autoassign.strategy.assignment.CostBasedAssignmentStrategy;

public class AutoAssignBuilder {

    private AssignmentStrategy assignmentStrategy = new CostBasedAssignmentStrategy();
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
