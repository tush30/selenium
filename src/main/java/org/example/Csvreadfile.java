package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Csvreadfile {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tushr\\OneDrive\\Desktop\\browserdriver\\chromedriver.exe");
//

        WebDriver driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        // Clear all browser cookies
        driver.manage().deleteAllCookies();

        // Set dynamic wait for page load (waits max 40 seconds)
        driver.manage().timeouts().pageLoadTimeout(40,TimeUnit.SECONDS);

        // Set implicit wait to wait for elements (waits max 30 seconds)
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // Open the target drag-and-drop test page
        driver.get("https://practicetestautomation.com/practice-test-login/");
        String CsvFile ="C:\\Users\\tushr\\IdeaProjects\\LaunchSelenium\\src\\main\\java\\org\\example\\passworduser.csv";
        try {
            CSVReader reader = new CSVReader(new FileReader(CsvFile));
            String [] nextLine;
            while((nextLine= reader.readNext())!=null){
                String username = nextLine[0];
                String password = nextLine[1];
                driver.findElement(By.name("username")).sendKeys(username);
                driver.findElement(By.name("password")).sendKeys(password);
                driver.findElement(By.id("submit")).click();

                Thread.sleep(3000);

                driver.findElement(By.linkText("Log out")).click();
                driver.findElement(By.name("username")).clear();
                driver.findElement(By.name("password")).clear();
            }
        }catch (IOException | CsvValidationException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
