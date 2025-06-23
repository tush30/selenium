package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MouseEffect {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tushr\\OneDrive\\Desktop\\browserdriver\\chromedriver.exe");


        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://spicejet.com/");

        Thread.sleep(3000);

        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//div[text()='Add-ons']"))).build().perform();

        Thread.sleep(3000);

        driver.findElement(By.xpath("//div[text()='Travel Assistance Services']")).click();
}


}
