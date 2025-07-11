package webdriver.part3;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_25_Javascript_Excutor {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;

    //Khai báo JSExecutor
    JavascriptExecutor jsExecutor;

    WebDriverWait explicitWait;
    Random random;
    String email;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();

        // Ép kiểu driver sang JSE - ép kiểu tường minh
        jsExecutor = (JavascriptExecutor) driver;
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        email= "automatio" + new Random().nextInt(9999)+ "@gmail.com";
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_() throws InterruptedException {


        jsExecutor.executeScript("window.location = 'https://live.techpanda.org/'");
//        explicitWait.until(ExpectedConditions.urlToBe("http://live.techpanda.org/"));
        Thread.sleep(5000);

        String getDomain = (String) jsExecutor.executeScript(" return document.domain");
        Assert.assertEquals(getDomain, "live.techpanda.org");

        String getURL = (String) jsExecutor.executeScript("return document.URL");
        Assert.assertEquals(getURL, "https://live.techpanda.org/");

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Mobile']")));
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Mobile']")));
        Thread.sleep(5000);

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Samsung Galaxy']" +
                "/parent::h2/following-sibling::div[@class='actions']/button")));
        jsExecutor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[text()='Samsung Galaxy']" +
                "/parent::h2/following-sibling::div[@class='actions']/button")));
        Thread.sleep(5000);

        String samsungText = (String) jsExecutor.executeScript("return document.documentElement.innerText");
        Assert.assertTrue(samsungText.contains("Samsung Galaxy was added to your shopping cart."));

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Customer Service']")));
        jsExecutor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[text()='Customer Service']")));
        Thread.sleep(5000);

        jsExecutor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.cssSelector("input#newsletter")));

        jsExecutor.executeScript("arguments[0].setAttribute('value','" + email + "')",driver.findElement(By.cssSelector("input#newsletter")));

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[title='Subscribe']")));
        jsExecutor.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("button[title='Subscribe']")));
        Thread.sleep(5000);
    }

    @Test
    public void TC_02_() {


    }

    // 3- Clean: Delete data test/account/close browser/...
//    @AfterClass
//    public void cleanBrowser(){
//        driver.quit();
//    }

}