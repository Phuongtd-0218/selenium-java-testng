package webdriver.part3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_21_Fixed_Popup {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_NgoaiNgu24h(){

        //Link trang test

        //Tạo 1 biến vị trí popup

        //Kiểm tra hiển thị

    }

    @Test
    public void TC_02_Facebook(){
        driver.get("https://www.facebook.com/");
        // findElemens
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

        By creatNewAcc = By.xpath("//div[text()='Create a new account']/parent::div/parent::div");

        //Confirm envisible
        Assert.assertTrue(driver.findElement(creatNewAcc).isDisplayed());

        //close register site
        driver.navigate().back();

        // Check popup isdisable yet?
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(creatNewAcc).size(),0);
    }

    @Test
    public void TC_03_Tiki_(){

        // Kiểm ta TH element không còn trong HTML

    }

    // 3- Clean: Delete data test/account/close browser/...
    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }

}