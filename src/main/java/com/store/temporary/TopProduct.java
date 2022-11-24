package com.store.temporary;

import java.util.Date;

public interface TopProduct {
    int getId();

    String getProduct_name();

    double getProduct_price();

    int getOrder_quantity();

    int getProduct_modelyear();

    String getProduct_image();

    String getBrand_name();

    Date getOrder_date();
}
