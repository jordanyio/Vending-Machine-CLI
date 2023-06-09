package com.techelevator;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class logMoney {
    private static final String LOG_PATH = "log.txt";
    private static int counter = 0;
    public static void logTransactions(String transaction) {
        if (counter == 0 ) {
            fileCleaner(LOG_PATH);
        }
        try ( PrintWriter writer = new PrintWriter(new FileWriter(LOG_PATH, true))) {
            LocalDateTime dateTime = LocalDateTime.now();   // gets current date/time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a"); // initializing the format for the date/time
            String formattedDate = dateTime.format(formatter);  // format according to the line above
            String logMessage = formattedDate + " " + transaction; // creates the log message string
            writer.println(logMessage);   // writes the message into the file.
        } catch(IOException e){
            System.out.println("Error occurred writing log file.");
            e.printStackTrace();
        }
    }
    public static void fileCleaner(String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write("");
            fileWriter.close();
            counter++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
