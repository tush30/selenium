package org.example;

import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CsvReadWrite {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tushr\\OneDrive\\Desktop\\browserdriver\\chromedriver.exe");

        String usernameToUpdate = "student1"; // Username whose row you want to update
        String newUsername = "student";
        String newPassword = "Password123";   // New password to update

        CsvReadWrite.update(usernameToUpdate,newUsername,newPassword);

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.get("https://practicetestautomation.com/practice-test-login/");

        String csvFile = "C:\\Users\\tushr\\IdeaProjects\\LaunchSelenium\\src\\main\\java\\org\\example\\csvwirte.csv";

        try (
                CSVReader reader = new CSVReader(new FileReader(csvFile))
        ) {
            List<String[]> updatedData = new ArrayList<>();
            // reading from next Line
            String[] nextLine =reader.readNext();

            // Read all lines and prepare modified content
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length < 2) continue; // Skip invalid lines
                String username = nextLine[0];
                String password = nextLine[1];
                String result;

                driver.findElement(By.name("username")).sendKeys(username);
                driver.findElement(By.name("password")).sendKeys(password);
                driver.findElement(By.id("submit")).click();
                Thread.sleep(2000);

                try {
                    if (driver.findElement(By.linkText("Log out")).isDisplayed()) {
                        result = "Login Successful";
                        driver.findElement(By.linkText("Log out")).click();
                    } else {
                        result = "Login Failed";
                    }
                } catch (Exception e) {
                    result = "Login Failed";
                }

                // Prepare updated row with result
                String[] updatedRow = new String[]{username, password, result};
                updatedData.add(updatedRow);

                driver.findElement(By.name("username")).clear();
                driver.findElement(By.name("password")).clear();

            }

            // Now overwrite the same file with updated content
            try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
                writer.writeNext(new String[]{"Username", "Password", "Result"}); // Header
                writer.writeAll(updatedData);
            }

        } catch (IOException | CsvValidationException | InterruptedException e) {
            e.printStackTrace();
        }


        driver.quit();
    }
    public static void update(String username, String newname, String newpassword) {
        String inputCsv = "C:\\Users\\tushr\\IdeaProjects\\LaunchSelenium\\src\\main\\java\\org\\example\\csvwirte.csv";

        try {
            // Step 1: Read all data
            CSVReader reader = new CSVReader(new FileReader(inputCsv));
            List<String[]> csvData = new ArrayList<>();
            String[] line;

            while ((line = reader.readNext()) != null) {
                // Step 2: Check if current row matches the username
                if (line[0].equals(username)) {
                    line[0] = newname;
                    line[1] = newpassword; // Modify the password column


                    System.out.println("Updated row for user: " + username);
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
