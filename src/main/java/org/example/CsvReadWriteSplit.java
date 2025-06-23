package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CsvReadWriteSplit {

    public static void main(String[] args) {
        String inputPath = "src/main/java/org/example/input.csv";
        String successPath = "src/main/java/org/example/success.csv";
        String failurePath = "src/main/java/org/example/failure.csv";

        WebDriver driver = setupDriver();
        processLoginCsv(driver, inputPath, successPath, failurePath);
        driver.quit();
    }

    // 🧩 Setup WebDriver
    public static WebDriver setupDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tushr\\OneDrive\\Desktop\\browserdriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }

    // Read and Process Credentials
    public static void processLoginCsv(WebDriver driver, String inputCsv, String successCsv, String failureCsv) {
        try (
                CSVReader reader = new CSVReader(new FileReader(inputCsv));
                CSVWriter successWriter = new CSVWriter(new FileWriter(successCsv));
                CSVWriter failureWriter = new CSVWriter(new FileWriter(failureCsv))
        ) {
            successWriter.writeNext(new String[]{"Username", "Password", "Result"});
            failureWriter.writeNext(new String[]{"Username", "Password", "Result"});

            reader.readNext(); // skip header

            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                String username = nextLine[0];
                String password = nextLine[1];



                boolean success = performLogin(driver, username, password);
                if (success) {
                    successWriter.writeNext(new String[]{username, password, "Login Successful"});

                } else {
                    failureWriter.writeNext(new String[]{username, password, "Login Failed"});

                }
            }

        } catch (IOException | CsvValidationException e) {
            System.err.println(" Error during file operations: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //  Login Attempt
    public static boolean performLogin(WebDriver driver, String username, String password) {
        try {
            driver.get("https://practicetestautomation.com/practice-test-login/");
            driver.findElement(By.name("username")).sendKeys(username);
            driver.findElement(By.name("password")).sendKeys(password);
            driver.findElement(By.id("submit")).click();
            Thread.sleep(2000); // simulate wait

            boolean loggedIn = driver.findElement(By.linkText("Log out")).isDisplayed();
            if (loggedIn) {
                driver.findElement(By.linkText("Log out")).click();
                return true;
            }
        } catch (Exception e) {
            // Element not found or other error
        }
        return false;
    }
}
