package webdriver.part3;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_18_User_Action {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;
    Actions action;
    WebDriverWait expWait;

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

//        driver.get("https://demo.nopcommerce.com");
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        action = new Actions(driver);
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_Hover() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));

        action.moveToElement(ageTextbox).perform();
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-tooltip-content")).isDisplayed());

        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");

    }

    @Test
    public void TC_02_Hover_Myntra() throws InterruptedException {
        driver.get("https://www.myntra.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();

        //Hover in KIDs
        action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
        Thread.sleep(3000);
        // C1- click by Webelement
//        driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']")).click();

        // C2- click by Action
        action.click(driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']"))).perform();
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb'] ")).getText(),"Kids Home Bath");
    }

     @Test
     public void TC_03_Hover_Fahasa() throws InterruptedException {
        driver.get("https://www.fahasa.com/");
        driver.manage().window().maximize();



//        expWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.fhs_dropdown_hover>span.icon_menu")));

        action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();

        action.moveToElement(driver.findElement(By.xpath("//span[text()='FOREIGN BOOKS']"))).perform();
         Thread.sleep(2000);

        driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='Economics']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Economics']")).isDisplayed());

    }

    // 3- Clean: Delete data test/account/close browser/...
    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }

}