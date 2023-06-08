package com.techelevator.view;

import com.techelevator.Inventory;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class Menu {
	private PrintWriter out;
	private Scanner in;
	Inventory inventory = new Inventory();
	private double feedMoney;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
		this.feedMoney = 0.0;
	}

	public void displayVendingMachineItems() {
		if (Inventory.getItemsList().size() == 0) {
			inventory.loadItemsFromFile("vendingmachine.csv");
		}
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


	public Items searchForItem() {
		System.out.print("Enter the slot of the item you want to purchase. (Enter 'm' to view the menu): ");
		String userIn = in.nextLine();

		if (userIn.equals("m")) {
			displayVendingMachineItems();
			return searchForItem(); // Ask for the slot choice again
		}

		List<Items> itemsList = inventory.getItemsList();
		for (Items item : itemsList) {
			if (userIn.equals(item.getSlot())) {
				if (item.getQUANTITY() > 0) {
					double itemPrice = item.getPrice();
					if (feedMoney >= itemPrice) {
						item.setQUANTITY(item.getQUANTITY() - 1); // Subtract 1 from quantity
						feedMoney -= itemPrice; // Subtract item price from feedMoney
						System.out.println("Here is your item: " + item.getName());
						displayMessage();
						System.out.println("Remaining funds: $" + feedMoney);
						return item;
					} else {
						System.out.println("Brokie! Please add more money.");
						return null;
					}
				} else {
					System.out.println("Item out of stock");
					return null;
				}
			}
		}
		System.out.println("Invalid slot choice");
		return null; // Item not found
	}

	public void feedMoneyCounter() {
		System.out.print("How much money are you depositing? >>> ");
		double money = in.nextDouble();
		in.nextLine();
		feedMoney += money;

		DecimalFormat decimalFormat = new DecimalFormat("##.##");
		feedMoney = Double.parseDouble(decimalFormat.format(feedMoney));

	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;

		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}
	private void displayMessage() {

	}
}
