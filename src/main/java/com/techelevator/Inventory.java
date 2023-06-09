package com.techelevator;

import com.techelevator.view.Items;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Inventory {                      // This will read the CSV file
    private static List<Items> itemsList;
    public Inventory() {
        itemsList = new ArrayList<>();
    }
    public void loadItemsFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length == 4) {
                    String slot = data[0];
                    String name = data[1];
                    double price = Double.parseDouble(data[2]);
                    String type = data[3];
                    Items item = new Items(slot, name, price, type);
                    itemsList.add(item);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Items> getItemsList() {
        return itemsList;
    }



}




