package webdriver.part3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
        driver.get(homePageUrl);
        //Giá trị truyền vào phải là link bắt đầu bằng giao thức 'http://' hoặc 'https://'

        //Đóng browser/tabs
//        driver.close();

        //Đóng browser
//        driver.quit();

        //Lấy ra title của page hiện tại
        //1-Lưu dữ liệu lại rồi kiểm tra sau
        String homePageTitle = driver.getTitle();

        Assert.assertEquals(homePageTitle,"nopCommerce demo store. Home page title");

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
        driver.findElement(By.xpath(""));

        // Đi tìm n element
        driver.findElements(By.xpath(""));

        driver.manage().timeouts().getScriptTimeout();


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