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
		boolean inMainMenu = true;

		while (true) {
			if (inMainMenu) { // main menu
				String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
				if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
					menu.displayVendingMachineItems();
				} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
					inMainMenu = false;
				} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
					System.out.println("System Exiting...");
					menu.giveChange();
					System.exit(1);
				} else if (choice.equals(MAIN_MENU_SECRET_OPTION)) {
					menu.salesReport();
				}
			} else { // purchase menu
				String choice2 = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
				if (choice2.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
					menu.feedMoneyCounter();
				} else if (choice2.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
					menu.searchForItem();
				} else {
					inMainMenu = true;
					System.out.println();
					System.out.println("Back to main menu...");
					System.out.println("Exiting will return your balance");
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
