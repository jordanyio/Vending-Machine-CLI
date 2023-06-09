package com.techelevator;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class logMoney {

    private static final String LOG_PATH = "log.txt";

    public static void logTransactions(double moneyDeposited, double moneySpent, double newBalance) {

        try {
            OutputStream outputStream = new FileOutputStream(LOG_PATH, true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter

        } catch(IOException e){

        }
    }


}
