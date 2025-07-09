package webdriver.part3;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_23_Handle_Window {

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

        CloseAllWindowWithoutParent(gihubWindowID);
    }


    @Test
    public void TC_02_TechPanda() throws InterruptedException {
        driver.get("https://live.techpanda.org/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //tìm tab moble
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();

        // verify hiển thị
        Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title.category-title h1")).getText(), "MOBILE");

        //Add Sony Xperia và Samsung Galaxy vào list compare
        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']" +
                "//a[text()='Add to Compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("ul.messages li.success-msg span")).getText(), "The product Sony Xperia has been added to comparison list.");

        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']" +
                "//a[text()='Add to Compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("ul.messages li.success-msg span")).getText(), "The product Samsung Galaxy has been added to comparison list.");


        //Click vào phím compare
        driver.findElement(By.cssSelector("div.block-content div.actions button")).click();

        //Switch sang web mới bật ra

        // Lấy ID trang hiện tại

        String TechPandaID = driver.getWindowHandle();

        // Lấy ra hết ID
        switchToWindowByTitle("Products Comparison List - Magento Commerce");

        //Verify by Window Title
//        Assert.assertEquals(driver.getTitle(),"Products Comparison List - Magento Commerce");

        //Close tab vừa hiện ra
        driver.close();

        //Quay về tab panda

        switchToWindowByTitle("Mobile");

        //Ấn clear All
        driver.findElement(By.cssSelector("div.block-content div.actions a")).click();
        Thread.sleep(5000);

        Alert alert = driver.switchTo().alert();
        alert.accept();


    }

    @Test
    public void TC_03_Cambridge() throws InterruptedException {
        driver.get("https://dictionary.cambridge.org/vi/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String homePageTitle = driver.getWindowHandle();
        System.out.println(homePageTitle);

        // Click Đăng nhập
        driver.findElement(By.xpath("//span[contains(@class,'cdo-login-button')]//span[text()='Đăng nhập']")).click();
        Thread.sleep(2000);

        //Switch to Tab đăng nhập

        //Lấy ra ID của các tab
        Set<String> allIDWindows = driver.getWindowHandles();

        //Cho switch lần lượt vào các trang web được mở
        for (String i : allIDWindows) {
            driver.switchTo().window(i);
            driver.manage().window().maximize();

            //Lấy ra title của trang vừa switch tới
            String iTitle = driver.getTitle();

            //Nếu title là Login thì sẽ thoát vòng for
            if (iTitle.equals("Login")) {
                break;
            }
        }

        // Click Login button

        driver.findElement(By.cssSelector("input[value = 'Log in']")).click();

        //Lấy ra error mess của email và password
        Assert.assertEquals(driver.findElement(By.cssSelector("input[data-gigya-name= 'loginID'] " +
                "~span.gigya-error-msg-active")).getText(), "This field is required");
        Assert.assertEquals(driver.findElement(By.cssSelector("input[data-gigya-name= 'password'] " +
                "~span.gigya-error-msg-active")).getText(), "This field is required");

        //Đóng trang login
        driver.close();
        Thread.sleep(3000);

        //switch về trang chính
        try {
            driver.switchTo().window(homePageTitle);
            Thread.sleep(2000);
        } catch (Exception exception) {
            System.out.println("No switch to homepage" + exception);
        }

        //Nhập từ khóa vào ô tìm kiếm sau đó verify kết quả hiển thị
        driver.findElement(By.cssSelector("input#searchword")).sendKeys("Code");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button[type='submit'] i.i-search")).click();
        Thread.sleep(1000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#cald4-1 ~div span.headword span"))
                .getText(), "code");
    }

    @Test
    public void TC_04_Selenium_4X() throws InterruptedException {
        driver.get("https://live.techpanda.org/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //open new tab in driver and switch to new tab, that is opened like End-User
        driver.switchTo().newWindow(WindowType.TAB).get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.cssSelector("button[title='Login']")).click();
        Thread.sleep(2000);

        //back to homepage
        switchToWindowByTitle("Home page");
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        Thread.sleep(2000);
        driver.navigate().back();
        Thread.sleep(2000);
    }

    // 3- Clean: Delete data test/account/close browser/...
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

    private void CloseAllWindowWithoutParent(String gihubWindowID) throws InterruptedException {
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String id : allWindowIDs) {
            if (!id.equals(gihubWindowID)) {
                driver.switchTo().window(id);
                driver.close();
                Thread.sleep(2000);
            }

        }
        //switch vào tab window cuối cùng
        driver.switchTo().window(gihubWindowID);
    }

    private void switchToWindowByTitle(String ExpectTitle) throws InterruptedException {
        // Lấy hết toàn bộ ID của window/tab
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            //switch vào trước
            driver.switchTo().window(id);
            Thread.sleep(2000);

            //Get title
            String pageTitle = driver.getTitle();

            //Nếu trung với title mình cần sẽ break
            if (pageTitle.equals(ExpectTitle)) {
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
            if (!id.equals(WindowID)) {
                // Thỏa mãn điều kiện sẽ swith qua
                driver.switchTo().window(id);
            }
        }
    }

}