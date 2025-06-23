package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class DisplayedEndbledMethods {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tushr\\OneDrive\\Desktop\\browserdriver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();//maximize window

        driver.manage().deleteAllCookies();// delete all cookies
        //dynamic Wait
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

        driver.get("https://api.cogmento.com/register/");

        // 1) isDesabled method applicable for all element
        boolean b = driver.findElement(By.id("sign-in-btn")).isDisplayed();
        System.out.println(b);

        // 2) isEnabled method
        boolean b1 = driver.findElement(By.id("sign-in-btn")).isEnabled();
        System.out.println(b1);

        // select check box
         driver.findElement(By.id("ageree")).click();
        boolean b3 = driver.findElement(By.id("sign-in-btn")).isEnabled();
        System.out.println(b3);

        //3) isSelected method only for applicable for checkbox, dropdown, radiobutton
        boolean b4 =  driver.findElement(By.id("ageree")).isSelected();
        System.out.println(b4);

        // 4) deSelect check box
        driver.findElement(By.id("ageree")).click();
        boolean b5 =  driver.findElement(By.id("ageree")).isSelected();
        System.out.println(b5);


    }
}
