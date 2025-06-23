package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HeadlessChrome {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tushr\\OneDrive\\Desktop\\browserdriver\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // run in headless mode
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);

        driver.get("https://practicetestautomation.com/practice-test-login/");
        System.out.println("Title before login: " + driver.getTitle());

        driver.findElement(By.name("username")).sendKeys("student");
        driver.findElement(By.name("password")).sendKeys("Password123");
        driver.findElement(By.id("submit")).click();

        System.out.println("Title after login: " + driver.getTitle());

        driver.quit();
    }
}
