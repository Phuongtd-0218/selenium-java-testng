package webdriver.part3;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.Logs;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowser_Commands {
    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        //Run with browser(Local)
        driver = new FirefoxDriver();

        //Remote(Gric Driver/ Cloud Tesing)
        // Cần kiến thức Networking
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    //Object là root class - mọi class sẽ coi object là class cha của nó - hiển thị không in đậm
    @Test
    public void TC_01_() {
        String homePageUrl = "https://demo.nopcommerce.com";
        driver.get(homePageUrl); //**
        //Giá trị truyền vào phải là link bắt đầu bằng giao thức 'http://' hoặc 'https://'

        //Đóng browser/tabs
//        driver.close(); //*

        //Đóng browser
//        driver.quit(); //**

        //Lấy ra title của page hiện tại
        //1-Lưu dữ liệu lại rồi kiểm tra sau
        String homePageTitle = driver.getTitle();

        Assert.assertEquals(homePageTitle, "nopCommerce demo store. Home page title");

        //Lấy ra URL của page hiện tại
        driver.getCurrentUrl();
        System.out.println(driver.getCurrentUrl());

        //Lấy ra Page source code
        String homePageSourceCode = driver.getPageSource();

        //Kiểm tra tương đối
        Assert.assertTrue(homePageSourceCode.contains("search-box-button"));

        //Lấy ra ID của tab/window đang active
        driver.getWindowHandle();

        //Lấy ra ID của tất cả tab/window đang có
        driver.getWindowHandles();

        // Đi tìm element
        driver.findElement(By.xpath("")); //**

        // Đi tìm n element
        driver.findElements(By.xpath("")); //**

        WebDriver.Options options = driver.manage();
        WebDriver.Timeouts timeOuts = driver.manage().timeouts();
        //Selenium ver3
        timeOuts.implicitlyWait(15, TimeUnit.SECONDS);

        //Selenium ver4
        //DÙng để chờ việc tìm element
        timeOuts.implicitlyWait(Duration.ofSeconds(15)); //**

        //Dùng để chờ việc page load
        timeOuts.pageLoadTimeout(Duration.ofSeconds(15));

        //Dùng để chờ 1 đoạn script được thực thi
        timeOuts.scriptTimeout(Duration.ofSeconds(200));

        WebDriver.Window window = driver.manage().window();
        //Thu nhỏ tab để chạy
        window.minimize();

        //Vẫn còn taskbar
        window.maximize(); //*

        //không còn taskbar
        window.fullscreen();


        window.setSize(new Dimension(1920, 1080));
        window.getSize();

        //Set vị trí của màn hình test so với màn hình hiện tại
        window.setPosition(new Point(0, 0));
        window.getPosition();

        //Lấy tất cả cookie: Test Class01 (Register tài khoản -lưu cookie lại)
        Set<Cookie> getCookie = options.getCookies(); //*

        options.getCookieNamed(".Nop.Antiforgery");

        //Xóa hết cookie
        options.deleteAllCookies();

        for (Cookie cookie : getCookie) {
            //Xóa theo thứ tự
            options.deleteCookie(cookie);
        }

        //Xóa cookie theo tên
        options.deleteCookieNamed(".Nop.Antiforgery");

        // Đến 1 Test class khác 02/03/04/...(Kông cần login - set cookie đã có vào đây rồi refresh lại)
        for (Cookie cookie : getCookie) {
            //Xóa theo thứ tự
            options.addCookie(cookie);
        }

        driver.navigate().refresh();//Login thành công

        //
        Logs log = driver.manage().logs();
        LogEntries logEntries = log.get("BROWSER");
        for (LogEntry logEn : logEntries) {
            System.out.println(logEn);
        }
        log.getAvailableLogTypes();

        WebDriver.Navigation navigation = driver.navigate();

        //Refresh / F5
        navigation.refresh();

        //Quay về trang trước
        navigation.back();

        //Chuyển tiếp trang
        navigation.forward();

        //Mở URL bất kì
        navigation.to("https://demo.nopcommerce.com/books");

//        try {
//            navigation.to(new URL("https://demo.nopcommerce.com/apparel"));
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }

        // swichTo - Alert/Iframe/Windows(Tabs)
        WebDriver.TargetLocator targetLocator = driver.switchTo();

        //Alert
        targetLocator.alert().accept(); //*
        targetLocator.alert().dismiss(); //*

        // Frame/Iframe
        targetLocator.frame(""); //*
        targetLocator.defaultContent(); //*

        //Windows
        targetLocator.window(""); //*

        //Lấy ra ID của tab/window đang active
        driver.getWindowHandle();   //*

        //Lấy ra tất cả ID của tab/window đang có
        driver.getWindowHandles();   //*

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