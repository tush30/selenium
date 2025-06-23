package org.example;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class HandleWindowPopup {
    public static void main(String[] args) throws InterruptedException {
        // Setup Chrome options
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tushr\\OneDrive\\Desktop\\browserdriver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        // Set timeouts
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Open target site
        driver.get("https://www.popupcheck.com/");

        // Click on "Advanced Pop-Up Test"
        driver.findElement(By.linkText("Advanced Pop-Up Test")).click();

        // Click on "Click-OnLoad Pop Up Stopper Test"
        driver.findElement(By.linkText("(3) “Click-OnLoad” Pop Up Stopper Test")).click();

        // Wait for pop-up to appear
        Thread.sleep(3000);

        // Get all window handles
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> it = handles.iterator();

        // Get parent and child window IDs
        String parentWindowId = it.next();
        String childWindowId = it.next();

        System.out.println("Parent Window ID: " + parentWindowId);
        System.out.println("Child Window ID: " + childWindowId);

        // Switch to child window
        driver.switchTo().window(childWindowId);
        System.out.println("Switched to child window: " + driver.getTitle());

        // Add actions for child window here...

        // Close child window and return to parent
        driver.close(); // close child
        driver.switchTo().window(parentWindowId);
        System.out.println("Returned to parent window: " + driver.getTitle());

        // Close parent window if needed
        driver.quit();
    }
}
