package com.techelevator.view;

import java.util.HashMap;
import java.util.Map;

public class Items {

    private String name;
    private double price;
    private String slot;
    private String type;
    private int QUANTITY;


    public Items(String slot, String name, double price, String type ) {
        this.name = name;
        this.price = price;
        this.slot = slot;
        this.type = type;
        this.QUANTITY = 5;
    }

    public Items(String slot) {
        this.slot = slot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(int QUANTITY) {
        this.QUANTITY = QUANTITY;
    }


}
