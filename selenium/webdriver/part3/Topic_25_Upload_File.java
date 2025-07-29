package webdriver.part3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Topic_25_Upload_File {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_Jquery_Upload_Single() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        By byUpload = By.xpath("//input[@type='file']");
//        WebElement uploadFileElement = driver.findElement();

        //Load file
        driver.findElement(byUpload).sendKeys(haGiangPath);
        Thread.sleep(2000);

        driver.findElement(byUpload).sendKeys(haNoiPath);
        Thread.sleep(2000);

        driver.findElement(byUpload).sendKeys(daNangPath);
        Thread.sleep(2000);

        //Verify file
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='da nang.jpg']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='ha giang.jpg']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='ha noi.jpg']")).isDisplayed());

        //Click upload file
        List<WebElement> listStartUpload = driver.findElements(By.cssSelector("table button.start"));
        for (WebElement startButton : listStartUpload) {
            startButton.click();
            Thread.sleep(2000);
        }

        //Verify upload success
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='da nang.jpg']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='ha giang.jpg']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='ha noi.jpg']")).isDisplayed());
    }

    @Test
    public void TC_02_Jquery_Upload_Multiple() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        By byUpload = By.xpath("//input[@type='file']");

        driver.findElement(byUpload).sendKeys(haGiangPath + "\n"
                + haNoiPath + "\n"
                + daNangPath);
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("div.row.fileupload-buttonbar button[type='submit']")).click();
        Thread.sleep(2000);

        //Verify upload success
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='da nang.jpg']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='ha giang.jpg']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='ha noi.jpg']")).isDisplayed());

    }

    // 3- Clean: Delete data test/account/close browser/...
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}