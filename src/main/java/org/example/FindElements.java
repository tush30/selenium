package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class FindElements {
    public static void main(String[] args) {
        // Set ChromeDriver path if needed
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tushr\\OneDrive\\Desktop\\browserdriver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        // Set timeouts
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Open Facebook
        driver.get("https://sv1.scriptinstall.rocks:2083/cpsess2199578552/frontend/x3/softaculous/index.live.php#!act=listsoftwares&cat=cms");

        // Find all <input> tags
        List<WebElement> inputList = driver.findElements(By.tagName("input"));

        System.out.println("Total input fields: " + inputList.size());

        for (WebElement input : inputList) {
            // Print input field type or name
            System.out.println("Input type: " + input.getAttribute("type"));
        }

        driver.quit(); // Close browser
    }
}
