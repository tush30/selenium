package org.example;

import org.openqa.selenium.By;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.concurrent.TimeUnit;

public class HeadlessBrowserHtmlUnitDriver {
//    public static void main(String[] args) throws InterruptedException {
//        // Creating HtmlUnitDriver instance (headless and lightweight)
//        // Note: By default, JavaScript is disabled — but newer versions enable it by default
//        HtmlUnitDriver driver = new HtmlUnitDriver();
//
//        // Maximize the browser window (may not have visual effect since headless)
//        driver.manage().window().maximize();
//
//        // Clear all browser cookies
//        driver.manage().deleteAllCookies();
//
//        // Set page load timeout
//        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
//
//        // Set implicit wait time
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//
//        // Navigate to the login page
//        driver.get("https://practicetestautomation.com/practice-test-login/");
//        System.out.println("Before Login title ===== " + driver.getTitle());
//
//        // Locate and fill in the login form fields
//        driver.findElement(By.name("username")).sendKeys("student");
//        driver.findElement(By.name("password")).sendKeys("Password123");
//
//        // Click the submit button
//        driver.findElement(By.id("submit")).click();
//
//        // Wait to ensure the page finishes loading (use WebDriverWait in real tests)
//        Thread.sleep(2000);
//
//        // Print the title after login
//        System.out.println("After Login title ===== " + driver.getTitle());
//
//        // Close the driver session
//        driver.quit();
//    }
}
