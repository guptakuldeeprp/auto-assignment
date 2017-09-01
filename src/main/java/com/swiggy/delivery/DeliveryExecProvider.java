package com.swiggy.delivery;

import java.util.List;

public interface DeliveryExecProvider {

    List<DeliveryExec> listDeliveryExecs();

    void assigned(DeliveryExec deliveryExec);

}
