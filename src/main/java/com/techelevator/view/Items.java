package com.techelevator.view;

import com.techelevator.Inventory;

import java.util.HashMap;
import java.util.List;
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

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getSlot() {
        return slot;
    }

    public String getType() {
        return type;
    }

    public int getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(int QUANTITY) {
        this.QUANTITY = QUANTITY;
    }
}
