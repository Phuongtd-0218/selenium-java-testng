package webdriver.part3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.time.Duration;


public class Topic_28_7_Mix {

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
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động

    @Test
    public void TC_01_Element_Found() {
        //Trường hợp tìm thấy element thì sẽ không bị ảnh hưởng bởi timeouts
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
    }

    @Test
    public void TC_02_Element_Not_Found_Only_Implicit(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        //Wait vs Implicit
        driver.findElement(By.cssSelector("input#automation"));

        //3- Không tìm thấy element nào
        // Ban đầu fineElement không tìm thấy
        // Tìm lại mà thấy Element thf không cần chờ hết tổng time còn lại
        // Tìm lại và hết 13s không thấy sẽ đánh fail
        //Show lỗi: NoSuchElementExption
       }

    @Test
    public void TC_03_Element_Not_Found_Only_Explicit() {
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#automation")));

    }

    @Test
    public void TC_04_() {

    }

//     3- Clean: Delete data test/account/close browser/...
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}