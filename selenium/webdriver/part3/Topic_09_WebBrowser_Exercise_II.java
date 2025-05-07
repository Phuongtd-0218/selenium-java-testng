package webdriver.part3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_WebBrowser_Exercise_II {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_Login_Empty_EmailAndPassword() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/login/");
        System.out.println("true");

        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(), "This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(), "This is a required field.");

    }

    @Test
    public void TC_02_Login_Invalid_Email() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        driver.findElement(By.cssSelector("input#email")).sendKeys("123@12345.34567");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("12345678");

        driver.findElement(By.cssSelector("button#send2")).click();

        driver.findElement(By.cssSelector("div#advice-validate-email-email")).isDisplayed();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void TC_03_Login_Password_Under_6Characters() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123");

        driver.findElement(By.cssSelector("button#send2")).click();

        driver.findElement(By.cssSelector("div#advice-validate-password-pass")).isDisplayed();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_04_Login_Incorrect_EmailOrPassword() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("12345678");

        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(),"Invalid login or password.");
    }

    // 3- Clean: Delete data test/account/close browser/...
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}