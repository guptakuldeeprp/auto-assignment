package com.swiggy.autoassign.delivery;

import com.swiggy.autoassign.entity.DeliveryExec;

import java.util.Collection;

public interface DeliveryExecProvider {

    Collection<DeliveryExec> listDeliveryExecs();

    void assigned(DeliveryExec deliveryExec);

}
