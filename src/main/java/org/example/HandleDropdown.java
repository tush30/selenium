package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class HandleDropdown {
    public static void main(String[] args) {
        // Set path to ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tushr\\OneDrive\\Desktop\\browserdriver\\chromedriver.exe");

        // Launch Chrome browser
        WebDriver driver = new ChromeDriver();  // launch chrome

        // Navigate to the URL
        driver.get("https://scgi.half.ebay.com/ws/");  // enter url

        // Handle dropdown
        Select select = new Select(driver.findElement(By.id("state")));
        select.selectByVisibleText("California");
    }
}
