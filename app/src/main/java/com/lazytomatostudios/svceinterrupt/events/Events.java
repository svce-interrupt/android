package com.lazytomatostudios.svceinterrupt.events;

/**
 * Created by drpayyne on 6/9/17.
 */

public class Events {

    private int number;
    private String eventsName;

    Events(int num, String name) {
        this.number = num;
        this.eventsName = name;
    }

    public int getNumber() {
        return number;
    }

    public String getEventsName() {
        return eventsName;
    }
}
