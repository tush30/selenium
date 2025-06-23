package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LunchTest {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tushr\\OneDrive\\Desktop\\browserdriver\\chromedriver.exe");


        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();//maxzimize window
        driver.manage().deleteAllCookies();// delete all cookies

        //dymic wait
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://practicetestautomation.com/practice-test-login/");
        driver.findElement(By.name("username")).sendKeys("student");
        driver.findElement(By.name("password")).sendKeys("Password123");
        driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"loop-container\"]/div/article/div[2]/div/div/div/a")).click();
       // driver.get("https://indiacampus.accenture.com/myzone/accenture/auth/login");

//
//        WebElement usernameInput = driver.findElement(By.cssSelector("input[formcontrolname='UserName']"));
//        usernameInput.sendKeys("tushrathod2002@gmail.com");
//        WebElement usernameInput1 = driver.findElement(By.cssSelector("input[formcontrolname='Password']"));
//        usernameInput1.sendKeys("TushAcce@3071");
//        // Switch to the reCAPTCHA iframe
//        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title='reCAPTCHA']")));
//
//// Click the checkbox
//        WebElement checkbox = driver.findElement(By.cssSelector("div.recaptcha-checkbox-border"));
//        checkbox.click();
//
//// Switch back to main content
//        driver.switchTo().defaultContent();
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-login")));
//        loginBtn.click();

        //driver.findElement(By.xpath("//*[@id=\"wrapper\"]/hp-login-page/div/section/div/div[2]/form/div[4]/button")).click();
        driver.get("https://ui.vision/demo/webtest/frames/");

        // Switch to frame 1 (index or name or ID can be used)
        driver.switchTo().frame(0); // or driver.switchTo().frame("frame1");

        // Locate the input field and type a value
     driver.findElement(By.name("mytext1")).sendKeys("Hello Frame 1!");
        Thread.sleep(3000);
        // Go back to main HTML
        driver.switchTo().defaultContent();

         //Switch to frame 2 and fill input
        driver.switchTo().frame(1);
        WebElement input2 = driver.findElement(By.name("mytext2"));
        input2.sendKeys("Hello Frame 2!");
        Thread.sleep(3000);
        // Return to main page again
        driver.switchTo().defaultContent();

        // Switch to frame 3
        driver.switchTo().frame(2);
        WebElement input3 = driver.findElement(By.name("mytext3"));
        input3.sendKeys("Hello Frame 3!");
        Thread.sleep(3000);
        // Close the browser
        driver.quit();
    }
}
