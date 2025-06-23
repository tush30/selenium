package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class DragDrop {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tushr\\OneDrive\\Desktop\\browserdriver\\chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--no-sandbox");
//
//        options.addArguments("user-data-dir=C:/ChromeTemp");  // Run in headless mode if needed

        WebDriver driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        // Clear all browser cookies
        driver.manage().deleteAllCookies();

        // Set dynamic wait for page load (waits max 40 seconds)
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);

        // Set implicit wait to wait for elements (waits max 30 seconds)
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // Open the target drag-and-drop test page
        driver.get("http://jqueryui.com/droppable/");

        // Switch to the frame that contains the draggable and droppable elements


        // Create an Actions object to perform drag and drop
        Actions action = new Actions(driver);

        // Perform drag and drop:
        // 1. Click and hold the source element (draggable)
        // 2. Move to the target element (droppable)
        // 3. Release the mouse
        // 4. Build the action and perform it
        action.clickAndHold(driver.findElement(By.id("draggable")))
                .moveToElement(driver.findElement(By.id("droppable")))
                .release()
                .build()
                .perform();

    }
}
