package com.store.temporary;

import java.util.Date;

public interface TopOrder {
    int getId();

    double getOrder_price();

    Date getOrder_date();

    Date getShip_date();

    String getCustomer_name();
}
