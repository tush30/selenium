package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ImplicitWait {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tushr\\OneDrive\\Desktop\\browserdriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        // Maximize the browser window
        driver.manage().window().maximize();

        // Clear all browser cookies before running the test
        driver.manage().deleteAllCookies();

        // Open the target website
        driver.get("http://www.half.ebay.com");

        // Set a page load timeout of 30 seconds
        // This means Selenium will wait up to 30 seconds for the page to load completely
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        // Set an implicit wait of 10 seconds
        // This means Selenium will wait up to 10 seconds when trying to find any element on the page
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Note: You can continue your automation steps after this
        // e.g., locating and interacting with web elements
    }
}
