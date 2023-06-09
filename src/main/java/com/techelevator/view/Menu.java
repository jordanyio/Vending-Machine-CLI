package com.techelevator.view;

import com.techelevator.Inventory;
import org.junit.vintage.engine.discovery.IsPotentialJUnit4TestClass;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import static com.techelevator.logMoney.logTransactions;

public class Menu {
	private PrintWriter out;
	private Scanner in;
	Inventory inventory = new Inventory();
	private double feedMoney;
	private int counter = 0;

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
						item.setQUANTITY(item.getQUANTITY() - 1);
						feedMoney -= itemPrice;
						System.out.println("Here is your item: " + item.getName());
						displayMessage(item.getType());
						String transaction = (item.getName() + " " + item.getSlot()
								+ " " + "$" + item.getPrice() + " $" + feedMoney);
						logTransactions(transaction);
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

		boolean isDoneDeposit = false;
		while(!isDoneDeposit) {
			System.out.print("How much money are you depositing? >>> ");
			double money = in.nextDouble();
			in.nextLine();
			String transaction = " $" + money + " ";

			System.out.print("Do you want to deposit more? Input y/n >>> ");
			if(in.nextLine().equals("n")) {
				isDoneDeposit = true;
			}
			feedMoney += money;
			floatLimit(feedMoney);
			String currentBalance = "Current Money Provided: $" + feedMoney;
			System.out.println("\n" + currentBalance + "\n");
			String transaction2 = "FEED MONEY " + transaction + " $" + feedMoney;
			logTransactions(transaction2);
		}
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
			//  an error message will be displayed below
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput
					+ " is not a valid option ***" + System.lineSeparator());
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
	private void displayMessage(String type) {
		switch (type) {
			case "Chip":
				System.out.println("Crunch Crunch, Yum!");
				break;
			case "Candy":
				System.out.println("Munch Munch, Yum!");
				break;
			case "Drink":
				System.out.println("Glug Glug, Yum!");
				break;
			case "Gum":
				System.out.println("Chew Chew, Yum!");
				break;
		}
	}

	public void giveChange() {
		Items item = null;
		String transaction = "GIVE CHANGE " + " $" + feedMoney + " $0.00";
		System.out.println("Have a nice day, here is your change!"  + " $" +feedMoney);
		floatLimit(feedMoney);
		double remainingChange = feedMoney;
		int quarters = (int)(remainingChange / 0.25);
		remainingChange %= 0.25;
		int dimes = (int)(remainingChange / 0.10);
		remainingChange %= 0.10;
		int nickels = (int)(remainingChange / 0.05);
		//prints the change using all the coins
		System.out.println("Quarters: " + quarters);
		System.out.println("Dimes: " + dimes);
		System.out.println("Nickels: " + nickels);
		feedMoney = 0;					// shows machine return all change to customer
		logTransactions(transaction);
	}

	public void salesReport() {
		double totalDollarSales = 0;
		System.out.println(">'''");
		List<Items> itemsList = Inventory.getItemsList();
		for (Items item : itemsList) {
			if (item.getQUANTITY() < 5) {
				String salesReport = "";
				totalDollarSales += (5 - item.getQUANTITY()) * item.getPrice();
				salesReport += ">" + item.getName() + "|" + (5 - item.getQUANTITY());
				System.out.println(salesReport);
			}
		}
		floatLimit(totalDollarSales);
		System.out.println( ">" + "\n" + "**TOTAL SALES** $" + totalDollarSales + "\n" + ">'''");
	}
	public void floatLimit(double money) {
		DecimalFormat decimalFormat = new DecimalFormat("##.##");
		feedMoney = Double.parseDouble(decimalFormat.format(money));
	}
}
