package com.h3rry.demoeventbus.event;

import com.h3rry.demoeventbus.model.City;

/**
 * Created by herry on 15/03/2016.
 */
public class MyEvent {

    public final String message;
    public final City city;

    public MyEvent(String message, City city) {
        this.message = message;
        this.city = city;
    }

}
