package webdriver.part3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_28_5_ExplicitWait_Loading {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động

    @Test
    public void TC_01_Less_Than() {
//        try {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start button")).click();

        //Điều kiện Wait
        WebElement getTextElement = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));

        Assert.assertEquals(getTextElement.getText(), "Hello World!");

//        } catch (Exception ex) {
//            //Fail
//            System.out.println("Error is: " + ex.toString());
//        }
        //Fail
    }

    @Test
    public void TC_02_Equal() {
        try {
            explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            driver.get("https://automationfc.github.io/dynamic-loading/");
            driver.findElement(By.cssSelector("div#start button")).click();

            //Điều kiện Wait
            WebElement getTextElement = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));

            Assert.assertEquals(getTextElement.getText(), "Hello World!");
        } catch (Exception ex) {
            //Fail
            System.out.println("Error is: " + ex.toString());
        }
        //Pass
    }

    @Test
    public void TC_03_Greater_Than() {
        try {
            explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            driver.get("https://automationfc.github.io/dynamic-loading/");
            driver.findElement(By.cssSelector("div#start button")).click();

            //Điều kiện Wait
            WebElement getTextElement = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));

            Assert.assertEquals(getTextElement.getText(), "Hello World!");
        } catch (Exception ex) {
            //Fail
            System.out.println("Error is: " + ex.toString());
        }
        //Pass
    }

    @Test
    public void TC_04_() {
        try {
            explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            driver.get("https://automationfc.github.io/dynamic-loading/");
            driver.findElement(By.cssSelector("div#start button")).click();

            //Điều kiện Wait -Visible (dành cho 1 element sắp xuất hiện)
            WebElement getTextElement = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));

            //Điều kiện Wait - Invisible (dành cho 1 element mà kỳ vọng nó biến mất)
            boolean confirmLoadingElement = explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
            Assert.assertTrue(confirmLoadingElement);

//            Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(),"Hello World!");

            //Điều kiện Wait Text
            explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#finish h4"),"Hello World!"));

//            Assert.assertEquals(getTextElement.getText(), "Hello World!");
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