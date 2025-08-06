package webdriver.part3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Topic_28_6_ExplicitWait_Ajax {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;
    WebDriverWait explicitWait;

    String uploadFolderPath = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;

    String haNoi = "ha noi.jpg";
    String daNang = "da nang.jpg";
    String haGiang = "ha giang.jpg";

    String haNoiPath = uploadFolderPath + haNoi;
    String daNangPath = uploadFolderPath + daNang;
    String haGiangPath = uploadFolderPath + haGiang;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động

    @Test
    public void TC_01_Calendar() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        //Wait and Verify Calendar element is displayed
        Assert.assertTrue(explicitWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_Panel1"))).isDisplayed());

        //Wait and Verify Text
        //C1:
        Assert.assertTrue(explicitWait.until(ExpectedConditions
                .textToBe(By.cssSelector("div.RadAjaxPanel>span"), "No Selected Dates to display.")));

        //Wait and click to element
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[text()='22']"))).click();

        //Wait ajax loading invisible
        Assert.assertTrue(explicitWait.until(ExpectedConditions
                .invisibilityOfElementLocated(By.cssSelector("div[id$='RadCalendar1']>div.raDiv"))));

        //Wait and Verify Text
        Assert.assertTrue(explicitWait.until(ExpectedConditions
                .textToBe(By.cssSelector("div.RadAjaxPanel>span"), "Tuesday, July 22, 2025")));

    }

    @Test
    public void TC_02_GoFiles() throws InterruptedException {
        // Step 1: Open Url https://gofile.io/?t=uploadFiles
        driver.get("https://gofile.io/?t=uploadFiles");


        // Wait to loading invisible
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#index_loader>div.animate-spin.h-16"))));

        //Find Element upload File
        By byElementUpload = By.xpath("//input[@type='file']");

        //Upload File
        driver.findElement(byElementUpload).sendKeys(haGiangPath + "\n"
                + haNoiPath + "\n"
                + daNangPath);
        Thread.sleep(3000);

        //Wait for loading invisible
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span#destinationFolder>div.animate-spin"))));

        //Wait for upload image loading invisible
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.flex.items-center > div.animate-spin"))));

        //Wait đến khi hiển thị text "Upload Complete"
        Assert.assertTrue(explicitWait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//div[@class='text-center']/h2[text()='Upload Complete']"))).isDisplayed());

        // Click link
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.items-center.text-sm >a"))).click();

        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#filemanager_loading>div.animate-spin"))));

        //Find element Play and DownLoad
        // Element Play

        List<WebElement> listElementPlay = driver.findElements(By.cssSelector("div.flex-row >button.item_play"));
        Assert.assertEquals(explicitWait.until(ExpectedConditions.visibilityOfAllElements(listElementPlay)).size(), 3);
        // Element Download
        List<WebElement> listElementDownload = driver.findElements(By.cssSelector("div.flex-row >button.item_download"));
        Assert.assertEquals(explicitWait.until(ExpectedConditions.visibilityOfAllElements(listElementDownload)).size(), 3);

        System.out.println(listElementPlay + "\n" + listElementDownload);
    }

    @Test
    public void TC_02_UploadFiles_Fix() throws InterruptedException {
        driver.get("https://gofile.io/?t=uploadFiles");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        //Wait cho loading ở màn hình upload không còn hiển thị
        Assert.assertTrue(explicitWait.until(ExpectedConditions.
                invisibilityOfElementLocated(By.cssSelector("div.animate-spin"))));

        //Wait cho Upload Files button được Click
//        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a#home_uploadFile "))).click();


        //Find Element upload File
        By byElementUpload = By.xpath("//input[@type='file']");

        //Upload File nhiều file  trong 1 lần
        driver.findElement(byElementUpload).sendKeys(haGiangPath + "\n"
                + haNoiPath + "\n"
                + daNangPath);

        //Wait cho loading icon ở màn upload không còn hiển thị
//        Assert.assertTrue(explicitWait.until(ExpectedConditions.
//                invisibilityOfElementLocated(By.cssSelector("div#index_upload div.animate-spin"))));

        //Wait cho các progressbar của các file biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.file-progressbar"))));

        //Wait đến khi hiển thị text "Upload Complete"
        Assert.assertTrue(explicitWait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//div[@class='text-center']/h2[text()='Upload Complete']"))).isDisplayed());

        //Wait and Click vào link
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#index_upload div.grid a"))).click();

        //Wait cho loading icon ở màn upload không còn hiển thị
        Assert.assertTrue(explicitWait.until(ExpectedConditions.
                invisibilityOfElementLocated(By.cssSelector("main#index_main div.animate-spin"))));
    }

    @Test
    public void TC_04_() {

    }

    // 3- Clean: Delete data test/account/close browser/...
//    @AfterClass
//    public void cleanBrowser() {
//        driver.quit();
//    }

}