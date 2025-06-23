package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Explicity {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tushr\\OneDrive\\Desktop\\browserdriver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();//maximize window

        driver.manage().deleteAllCookies();// delete all cookies

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

        driver.get("https://www.facebook.com/");
        clickOn(driver, driver.findElement(By.xpath("//button[text()='Log in']")),20);

    }

    public static void clickOn(WebDriver driver,  WebElement locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

}
