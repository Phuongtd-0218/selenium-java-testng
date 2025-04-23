package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_03_Selenium_XPath_CSS {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();
        driver.get("https://live.techpanda.org");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_(){
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));

//        driver.get("https://live.techpanda.org/index.php/checkout/cart/");
//        driver.findElement(By.xpath("//li[@class='success-msg']//span"));

        driver.get("https://demo.nopcommerce.com/");
        WebElement clickDropList = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Computers ']"));

    }

    @Test
    public void TC_02_(){


    }

    // 3- Clean: Delete data test/account/close browser/...
    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }

}