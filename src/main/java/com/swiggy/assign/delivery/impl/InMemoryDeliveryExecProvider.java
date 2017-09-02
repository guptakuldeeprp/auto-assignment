package com.swiggy.assign.delivery.impl;

import com.swiggy.assign.entity.DeliveryExec;
import com.swiggy.assign.delivery.DeliveryExecProvider;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;


public class InMemoryDeliveryExecProvider implements DeliveryExecProvider {
    // if concurrent access is not required, we can replace with simple List
    private ConcurrentLinkedQueue<DeliveryExec> deliveryExecs = new ConcurrentLinkedQueue<>();

    public InMemoryDeliveryExecProvider(Collection<DeliveryExec> deliveryExecs) {
        this.deliveryExecs.addAll(deliveryExecs);
    }

    public Collection<DeliveryExec> listDeliveryExecs() {
        return deliveryExecs;
    }

    public void assigned(DeliveryExec deliveryExec) {
        deliveryExecs.remove(deliveryExec);
    }
}
