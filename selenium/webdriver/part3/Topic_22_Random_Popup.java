package webdriver.part3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.time.Duration;

public class Topic_22_Random_Popup {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_Javacodegeeks() throws InterruptedException {
        // khoi tao trang web
        driver.get("https://www.javacodegeeks.com/");


        //tim popup
        By newLetterPopup = By.xpath("//div[@data-title='Newsletter Free eBooks' and not(contains(@style,'display:none'))]");

        // Hien thi thi close di, neu khong hien thi thi thuc hien tiep
        if (driver.findElements(newLetterPopup).size() > 0
                && driver.findElements(newLetterPopup).get(0).isDisplayed()) {
            System.out.println("Go to If");
            //close popup
            driver.findElement(By.xpath("//div[@data-title='Newsletter Free eBooks' and not(contains(@style,'display:none'))]//a[contains(@onclick,'lepopup_close')]")).click();
            Thread.sleep(2000);
        }

        System.out.println("ignore if");
        //Tim kiem search
        driver.findElement(By.cssSelector("form#search input#search-input")).sendKeys("Java");
        driver.findElement(By.cssSelector("form#search button#search-submit")).click();

        //Kiem tra ket qua
        Assert.assertTrue(driver.findElement(By.cssSelector("header >h1.page-title")).isDisplayed());

    }

    @Test
    public void TC_02_dehieuVN() {
        driver.get("https://www.dehieu.vn/");
        By popupText = By.xpath("//h5[text()='Form Đăng Ký']/parent::div/parent::div");

        if (driver.findElements(popupText).size() > 0
                && driver.findElements(popupText).get(0).isDisplayed()) {
            System.out.println("Close popup");
            Assert.assertTrue(driver.findElement(popupText).isDisplayed());
            driver.findElement(By.xpath("//h5[text()='Form Đăng Ký']/following-sibling::button")).click();
            //check popup con hien thi khong
//            Assert.assertEquals(driver.findElements(popupText).size(),0);
        }

        //tiep tuc xu ly
        System.out.println();
        driver.findElement(By.cssSelector("input.search-form")).sendKeys("Thiết kế điện biệt thự - Nhà liền kề");
        driver.findElement(By.cssSelector("button.header-search")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.course-item-detail a")).getAttribute("title"),"Thiết kế điện biệt thự - Nhà liền kề");


    }

    // 3- Clean: Delete data test/account/close browser/...
//    @AfterClass
//    public void cleanBrowser() {
//        driver.quit();
//    }

}