package com.techelevator;

import com.techelevator.view.Items;
import com.techelevator.view.Menu;

import java.util.List;

public class VendingMachineCLI {
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_SECRET_OPTION = "*Sales Report";

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";

	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_SECRET_OPTION};
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};


	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {

		while (true) {

			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				displayVendingMachineItems();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				String choice2 = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
				if (choice2.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
					searchProduct();
					String choiceProduct = menu.getUserSlotChoice();
					searchItemByName(choiceProduct);

				}

//				ProductSearcher pSearch = new ProductSearcher();
//				pSearch.searchProduct();
//
//				System.out.println("Fail============================");
//

				System.out.println();                                    // displays list of all  items
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("System Exiting.");
				System.exit(1);
			}
		}
	}

	public void searchProduct() {
		System.out.print("Please enter a product >>> " );

		String searchTerm = "";

		//searchTerm = menu.getUserSlotChoice();

		//foundItem = searchItemByName(searchTerm);
//
//		if (foundItem != null) {
//			System.out.println("Item found: " +foundItem.getName());
//
//		} else {
//			System.out.println("Product not found");
//		}
//

	}


	private Items searchItemByName(String searchTerm) {
		Items foundItem = null;
		VendingMachineInventory inventory = new VendingMachineInventory();
		inventory.loadItemsFromFile("vendingmachine.csv");
		List<Items> itemsList = inventory.getItemsList();
		for (Items item : itemsList) {
			System.out.println(item.getSlot() + item.getName());
			System.out.println("try harder");
			if (item.getSlot().equals(searchTerm)) {
				foundItem = item;
				return foundItem;
				System.out.println("Equals");
			} else {
				System.out.println("this is null");
			}
		} return foundItem;

	} 


	private void currentMoney(double userInput) {
		double currentMoney = 0;
		currentMoney += userInput;
	}


	private void displayVendingMachineItems() {
		VendingMachineInventory inventory = new VendingMachineInventory();
		inventory.loadItemsFromFile("vendingmachine.csv");
		List<Items> itemsList = inventory.getItemsList();
		for (Items item : itemsList) {
			String slot = item.getSlot();
			String name = item.getName();
			double price = item.getPrice();
			String type = item.getType();
			String status = (item.getQUANTITY() > 0) ? String.valueOf(item.getQUANTITY()) : "SOLD OUT";
			if (status.equals("SOLD OUT")) {
				System.out.println(slot + " " + status);
			} else {
				System.out.println(slot + " | " + name + " | $" + price + " | " + type + " | ");
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
