package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.time.Duration;

public class HandelTable {
    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tushr\\OneDrive\\Desktop\\browserdriver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();//maximize window

        driver.manage().deleteAllCookies();// delete all cookies
        //dynamic Wait
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

        File file = new File("C:\\Users\\tushr\\IdeaProjects\\LaunchSelenium\\src\\main\\java\\org\\example\\table.html");
        String filePath = "file:///" + file.getAbsolutePath().replace("\\", "/");
        driver.get(filePath);
//        String beforepath= "//*[@id=\"contactTable\"]/tbody/tr[";
//        String afterpath = "]/td[2]";
//        for(int i=1;i<=10;i++){
//            String names= driver.findElement(By.xpath(beforepath+i+afterpath)).getText();
//            System.out.println(names);
//            if(names.contains("Kavya Kaba")){
//                driver.findElement(By.xpath("//*[@id=\"contactTable\"]/tbody/tr["+i+"]/td[1]/input")).click();
//            }
//        }
        driver.findElement(By.xpath("//td[contains(text(),'Kavya Kaba')]/parent::tr//preceding-sibling::tr//preceding-sibling::td")).click();
    }
}
