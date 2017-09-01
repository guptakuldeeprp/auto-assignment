package com.swiggy.delivery.impl;

import com.swiggy.delivery.DeliveryExec;
import com.swiggy.delivery.DeliveryExecProvider;

import java.util.List;
import java.util.Set;

public class InMemoryDeliveryExecProvider implements DeliveryExecProvider {

    private List<DeliveryExec> deliveryExecs;

    public InMemoryDeliveryExecProvider(List<DeliveryExec> deliveryExecs) {
        this.deliveryExecs = deliveryExecs;
    }

    public List<DeliveryExec> listDeliveryExecs() {
        return deliveryExecs;
    }

    public void assigned(DeliveryExec deliveryExec) {
        deliveryExecs.remove(deliveryExec);
    }
}
