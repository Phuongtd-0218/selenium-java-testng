package webdriver.part3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_28_3_Static_Wait {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_Dont_Set() {
        try {
            driver.get("https://automationfc.github.io/dynamic-loading/");
            driver.findElement(By.cssSelector("div#start button")).click();
            Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
        } catch (Exception ex) {
            System.out.println("Error is: " + ex.toString());
        }

        //Fail
    }

    @Test
    public void TC_02_Less_Than() {
        try {
            driver.get("https://automationfc.github.io/dynamic-loading/");
            driver.findElement(By.cssSelector("div#start button")).click();
            Thread.sleep(4000);
            Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));//Áp dụng cho findelement ở các câu lệnh, hàm tiếp theo
        } catch (Exception ex) {
            //Fail
            System.out.println("Error is: " + ex.toString());
        }
        //Fail
    }

    @Test
    public void TC_03_Equal() {
        try {
            driver.get("https://automationfc.github.io/dynamic-loading/");
            driver.findElement(By.cssSelector("div#start button")).click();
            Thread.sleep(5000);
            Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
        } catch (Exception ex) {
            //Fail
            System.out.println("Error is: " + ex.toString());
        }
        //Pass
    }

    @Test
    public void TC_04_Greater_Than() {
        try {
            driver.get("https://automationfc.github.io/dynamic-loading/");
            driver.findElement(By.cssSelector("div#start button")).click();
            Thread.sleep(6000);
            Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
        } catch (Exception ex) {
            //Fail
            System.out.println("Error is: " + ex.toString());
        }
        //Pass
    }

    // 3- Clean: Delete data test/account/close browser/...
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}