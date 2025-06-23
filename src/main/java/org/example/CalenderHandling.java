package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CalenderHandling {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tushr\\OneDrive\\Desktop\\browserdriver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        // Clear all cookies
        driver.manage().deleteAllCookies();

        // Set timeouts
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // Open the webpage
        driver.get("https://www.globalsqa.com/demo-site/datepicker/");
        Thread.sleep(3000);

        // Select the "DropDown DatePicker" tab
        driver.findElement(By.xpath("//li[text()='DropDown DatePicker']")).click();
        Thread.sleep(3000);

        // Switch into the iframe first
        WebElement iframe = driver.findElements(By.className("demo-frame")).get(1);
        driver.switchTo().frame(iframe);

        // Open the calendar picker
        driver.findElement(By.id("datepicker")).click();

        System.out.println("Datepicker opened");

        // Desired Date
        String date = "08/21/2022";
        String[] datearr = date.split("/");
        int month = Integer.parseInt(datearr[0]);
        String day = datearr[1];
        int year = Integer.parseInt(datearr[2]);

        // Wait until the picker is rendered
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-datepicker-month")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-datepicker-year")));

        // Select the appropriate month
        Select selectMonth = new Select(driver.findElement(By.className("ui-datepicker-month")));
        selectMonth.selectByIndex(month - 1);

        // Select the appropriate year
        Select selectYear = new Select(driver.findElement(By.className("ui-datepicker-year")));
        selectYear.selectByVisibleText(String.valueOf(year));

      //  //*[@id="ui-datepicker-div"]/table/tbody/tr[1]/td[6]/a
        // //*[@id="ui-datepicker-div"]/table/tbody/tr[2]/td[1]/a
        // //*[@id="ui-datepicker-div"]/table/tbody/tr[3]/td[1]/a
       // //*[@id="ui-datepicker-div"]/table/tbody/tr[1]/td[7]/a
       // //*[@id="ui-datepicker-div"]/table/tbody/tr[6]/td[1]/a
        // Select the appropriate day
       // driver.findElement(By.linkText(day)).click();

        String beforexpath = "//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[";
        String after = "]/td[";

        final int totalweekdays = 7;
        boolean flag = false;

        for (int row = 1; row < totalweekdays; row++) {
            for (int col = 1; col <= totalweekdays; col++) {
                // Get the <td> first
                WebElement cell = driver.findElement(By.xpath(beforexpath + row + after + col + "]"));
                String dayval = cell.getText().trim();

                if (dayval.equals(day)) {
                    // Now click the <a> tag within the cell
                    WebElement dateToSelect = wait.until(
                            ExpectedConditions.elementToBeClickable(By.xpath(beforexpath + row + after + col + "]/a"))
                    );
                    dateToSelect.click();

                    driver.switchTo().defaultContent();

                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }

        System.out.println("Date successfully selected.");

        driver.quit();



    }
}

