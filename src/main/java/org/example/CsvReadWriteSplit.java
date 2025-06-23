package org.example; // Package declaration

// Importing necessary libraries
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class CsvReadWriteSplit {

    public static void main(String[] args) {
        // File paths where input and output CSVs are located
        String inputPath = "src/main/java/org/example/input.csv";
        String successPath = "src/main/java/org/example/success.csv";
        String failurePath = "src/main/java/org/example/failure.csv";

        // Setup the browser and store driver instance
        WebDriver driver = setupDriver();

        // Call method to process login for each row in CSV
        processLoginCsv(driver, inputPath, successPath, failurePath);

        // After everything is done, close the browser
        driver.quit();
    }

    // Method to setup Chrome browser
    public static WebDriver setupDriver() {
        // Path where my ChromeDriver is saved
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tushr\\OneDrive\\Desktop\\browserdriver\\chromedriver.exe");

        // Launch new Chrome browser
        WebDriver driver = new ChromeDriver();

        // Maximize window
        driver.manage().window().maximize();

        // Delete any previous cookies
        driver.manage().deleteAllCookies();

        // Set wait time for page load
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);

        // Set implicit wait for element to load
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // Return the browser instance
        return driver;
    }

    // This method reads CSV, performs login, and writes output to success/failure files
    public static void processLoginCsv(WebDriver driver, String inputCsv, String successCsv, String failureCsv) {
        try {
                // Reading input CSV
                CSVReader reader = new CSVReader(new FileReader(inputCsv));

                // Writers to save success and failure records
                CSVWriter successWriter = new CSVWriter(new FileWriter(successCsv));
                CSVWriter failureWriter = new CSVWriter(new FileWriter(failureCsv));

            // Write headers for both files
            successWriter.writeNext(new String[]{"Username", "Password", "Result"});
            failureWriter.writeNext(new String[]{"Username", "Password", "Result"});

            // Skip first line (header) in input CSV
            reader.readNext();

            String[] nextLine;

            // Loop through each row (username, password)
            while ((nextLine = reader.readNext()) != null) {
                // Get username and password
                String username = nextLine[0];
                String password = nextLine[1];

                // Call login method and store result message
                String msg = performLogin(driver, username, password);

                // If login successful, write to success file
                if (msg.equals("Logged In Successfully")) {
                    successWriter.writeNext(new String[]{username, password, msg});
                } else {
                    // Else write to failure file
                    failureWriter.writeNext(new String[]{username, password, msg});
                }
            }

        } catch (IOException | CsvValidationException e) {
            // Print error if reading or writing file fails
            System.err.println(" Error during file operations: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // This method performs login operation and returns success or error message
    public static String performLogin(WebDriver driver, String username, String password) {
        try {
            // Open login page
            driver.get("https://practicetestautomation.com/practice-test-login/");

            // Enter username
            driver.findElement(By.name("username")).sendKeys(username);

            // Enter password
            driver.findElement(By.name("password")).sendKeys(password);

            // Click on login button
            driver.findElement(By.id("submit")).click();

            // Wait 2 seconds for page to load
            Thread.sleep(2000);

            // Check if success message is visible and Get success message text
            String loggedmsg = driver.findElement(By.xpath("//*[text()='Logged In Successfully']")).getText();

            // if msg is matched to desired msg then logout and return msg  loggedmsg
            if (loggedmsg.equals("Logged In Successfully")) {

                // Click logout
                driver.findElement(By.linkText("Log out")).click();

                // Return success message
                return loggedmsg;
            }

        } catch (Exception e) {
            // If any issue (like wrong credentials), catch it
            System.out.println(" Error during login");
        }

        // If login fails, get error message shown on page
        String failedmsg = driver.findElement(By.id("error")).getText();

        // Return failure message
        return failedmsg;
    }
}
