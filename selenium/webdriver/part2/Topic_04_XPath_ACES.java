package webdriver.part2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_XPath_ACES {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();

        driver.get("https://live.techpanda.org/index.php/mobile.html");
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_() {

        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button"));
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/ancestor::div[@class='product-info']/child::div[@class='actions']/button"));
        driver.findElement(By.xpath("//"));
        driver.findElement(By.xpath("//"));

    }

    @Test
    public void TC_02_() {


    }

    // 3- Clean: Delete data test/account/close browser/...
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}