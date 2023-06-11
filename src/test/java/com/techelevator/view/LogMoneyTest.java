package com.techelevator.view;

import com.techelevator.logMoney;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.techelevator.logMoney.logTransactions;

public class LogMoneyTest {

    private static final String LOG_PATH = "log.txt";

    @BeforeEach
    public void before() {
        // delete before test
        deleteLogFile();
    }

    @Test
    public void testLogTransactions() throws IOException {
        // calls method with sample
        String transaction = "Sample transaction";
        logTransactions(transaction);

        //Read content of log file
        String logContent = readLogFile();

        //makes sure message is present in file.
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        String formattedDate = dateTime.format(formatter);
        String logMessage = formattedDate + " " + transaction;
        Assertions.assertTrue(logContent.contains(logMessage), "Log message not found in the file.");
    }
    @Test
    public void testLogTransactionsCorruptAttempt() throws IOException {
        // calls method with a corrupt sample
        String corruptTransaction = "Corrupt Transaction";
        logMoney.logTransactions(corruptTransaction);
        //  reads content of the log file
        String logContent = readLogFile();

        // verifies log message isnt in file.
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        String formattedDate = dateTime.format(formatter);
        String logMessage = formattedDate + " " + corruptTransaction;
        Assertions.assertTrue(logContent.contains(logMessage), "Corrupt log message found in file.");
    }

    private String readLogFile() throws IOException {
        // reads  content of log
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(LOG_PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");

        }
        reader.close();
        return content.toString();
    }
    private void deleteLogFile() {
        // deletes log
        Path logFilePath = Paths.get(LOG_PATH);
        try {
            Files.deleteIfExists(logFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

