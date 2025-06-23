package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvRowModifier {
    public static void main(String[] args) {
        String inputCsv = "C:\\Users\\tushr\\IdeaProjects\\LaunchSelenium\\src\\main\\java\\org\\example\\ModifyData.csv";
        String usernameToUpdate = "student1"; // Username whose row you want to update
        String newUsername = "student";
        String newPassword = "Password123";   // New password to update
        String newresult="passed";
        try {
            // Step 1: Read all data
            CSVReader reader = new CSVReader(new FileReader(inputCsv));
            List<String[]> csvData = new ArrayList<>();
            String[] line;

            while ((line = reader.readNext()) != null) {
                // Step 2: Check if current row matches the username
                if (line[0].equals(usernameToUpdate)) {
                    line[0] = newUsername;
                    line[1] = newPassword;
                    line[2] = newresult;

                    System.out.println("Updated row for user: " + usernameToUpdate);
                }
                csvData.add(line); // Add (possibly modified) row to list
            }
            reader.close();

            // Step 3: Write modified data back to the file
            CSVWriter writer = new CSVWriter(new FileWriter(inputCsv));
            writer.writeAll(csvData);
            writer.close();

            System.out.println("CSV updated successfully.");

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}

