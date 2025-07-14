package webdriver.part3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_26_Wait_Element_Status {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_Visible() {
        driver.get("https://www.facebook.com");

        //Element có trên UI và trong cây HTML
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
    }

    @Test
    public void TC_02_Invisible() {
        driver.get("https://www.facebook.com");
        driver.findElement(By.xpath("//a[text()='新しいアカウントを作成']")).click();

        //Element không có trên UI nhưng có trong HTML
        //Nhập email sau đó textbox sẽ hiển thị, kiểm tra trước khi nhập thì textbox đó vẫn có trong HTML nhưng lại không hiển thị trên UI vì cần đk hiển thị
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));

        driver.navigate().back();

        //Element không có trên UI và không có trong HTML / Do backup lại Homepage
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
    }

    @Test
    public void TC_03_Presence() throws InterruptedException {
        driver.get("https://www.facebook.com");
        driver.findElement(By.xpath("//a[text()='新しいアカウントを作成']")).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='reg_email__']")));
        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("phuong@gmail.com");
        Thread.sleep(3000);

        //Element có trong UI và HTML
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));

        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        Thread.sleep(3000);
        //Element không có trên UI nhưng có trên HTML
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));

    }

    @Test
    public void TC_02_Staleness() {
        driver.get("https://www.facebook.com");
        driver.findElement(By.xpath("//a[text()='新しいアカウントを作成']")).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='reg_email__']")));

        // Confirm Email có trong HTML
        WebElement confirmEmail = driver.findElement(By.cssSelector("input[name='reg_email_confirmation__']"));

        driver.navigate().back();

        // Element không có trong HTML
        explicitWait.until(ExpectedConditions.stalenessOf(confirmEmail));
    }


    // 3- Clean: Delete data test/account/close browser/...
//    @AfterClass
//    public void cleanBrowser() {
//        driver.quit();
//    }

}