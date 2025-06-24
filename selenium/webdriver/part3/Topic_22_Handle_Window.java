package webdriver.part3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_22_Handle_Window {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_Github() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        // Lấy ra ID của window
        String gihubWindowID = driver.getWindowHandle();
        System.out.println("Page ID = " + gihubWindowID);
        System.out.println("Page Title = " + driver.getTitle());
        System.out.println("Page URL = " + driver.getCurrentUrl());

        // Click vào Google link -> sẽ bật lên 1 tab mới và tự nhảy qua
        // Code selenium sẽ không nhảy qua mà nó sẽ ở tab cũ
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//        System.out.println("Github window ID = " + driver.getWindowHandle());

        //switch to tab Google
        swithToWindowByID(gihubWindowID);
        Thread.sleep(5000);

        String googleWindowID = driver.getWindowHandle();

        driver.findElement(By.cssSelector("textarea[name = 'q']")).sendKeys("Selenium Web Driver");
        driver.findElement(By.cssSelector("textarea[name = 'q']")).submit();
        Thread.sleep(5000);

        //Swith về tab Github
        swithToWindowByID(googleWindowID);
        Thread.sleep(3000);

        //Click vào link FB
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        Thread.sleep(3000);
        String facebookWindowID = driver.getWindowHandle();



        switchToWindowByTitle("Selenium WebDriver");
        System.out.println("page Title: " + driver.getTitle());
    }

    private void switchToWindowByTitle(String ExpectTitle) throws InterruptedException {
        // Lấy hết toàn bộ ID của window/tab
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String id : allWindowIDs)
        {
            //switch vào
            driver.switchTo().window(id);
            Thread.sleep(2000);

            //Get title
            String pageTitle = driver.getTitle();

            //Nếu trung với title mình cần sẽ break
            if (pageTitle.equals(ExpectTitle)){
                break;
            }
        }
    }

    // Chỉ đúng với 2 window hoặc tab
    private void swithToWindowByID(String WindowID) {
        // Lấy hết ID của các tab
        Set<String> allWindowIDs = driver.getWindowHandles();
        // Dùng vòng lặp để duyệt
        for (String id : allWindowIDs) {
            if (!id.equals(WindowID)){
                // Thỏa mãn điều kiện sẽ swith qua
                driver.switchTo().window(id);
            }
        }
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