package com.techelevator.view;

import com.techelevator.Inventory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class InventoryTest {

    @Test
    public void testLoadItemsFromFile_EmptyFile() {
        // test empty file
        Inventory inventory = new Inventory();
        inventory.loadItemsFromFile("empty.csv");
        List<Items> itemsList = Inventory.getItemsList();
        Assertions.assertEquals(0, itemsList.size(), "Items list should be empty");
    }

    @Test
    public void testLoadItemsFromFile_ValidFile() {
        // test size
        Inventory inventory = new Inventory();
        inventory.loadItemsFromFile("vendingmachine.csv");
        List<Items> itemsList = Inventory.getItemsList();
        Assertions.assertEquals(16, itemsList.size(), "Items list should contain 16 items");

        // test first item
        Items item1 = itemsList.get(0);
        Assertions.assertEquals("A1", item1.getSlot(), "Incorrect slot for  Potato Crisps");
        Assertions.assertEquals("Potato Crisps", item1.getName(), "Incorrect name for  Potato Crisps");
        Assertions.assertEquals(3.05, item1.getPrice(), "Incorrect price for  Potato Crisps");
        Assertions.assertEquals("Chip", item1.getType(), "Incorrect type for Potato Crisps");

        // test last item
        Items lastItem = itemsList.get(itemsList.size() - 1);
        Assertions.assertEquals("D4", lastItem.getSlot(), "Incorrect slot for  Triplemint");
        Assertions.assertEquals("Triplemint", lastItem.getName(), "Incorrect name for  Triplemint");
        Assertions.assertEquals(0.75, lastItem.getPrice(), "Incorrect price for  Triplemint");
        Assertions.assertEquals("Gum", lastItem.getType(), "Incorrect type for Triplemint");

    }
}