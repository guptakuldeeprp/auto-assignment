package com.swiggy.assign.delivery;

import com.swiggy.assign.entity.DeliveryExec;

import java.util.Collection;

public interface DeliveryExecProvider {

    Collection<DeliveryExec> listDeliveryExecs();

    void assigned(DeliveryExec deliveryExec);

}
