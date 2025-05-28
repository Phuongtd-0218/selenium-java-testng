package webdriver.part3;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_20_Iframe_DOExPlease {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();

    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_Ifram_Toidicodedao() throws InterruptedException {
        driver.get("https://toidicodedao.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        WebElement iframeElement = driver.findElement(By.cssSelector("div.fb_iframe_widget iframe"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",iframeElement);
        Thread.sleep(2000);
        //switch to Iframe
        driver.switchTo().frame(iframeElement);

        String followerText = driver.findElement(By.xpath("//a[@title='Tôi đi code dạo']/parent::div/following-sibling::div[text()]")).getText();

        System.out.println(followerText);
    }

    @Test
    public void TC_02_(){
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));



    }

    @Test
    public void TC_03_Frame(){
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));


    }

    // 3- Clean: Delete data test/account/close browser/...
    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }

}