package webdriver.part3;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v135.indexeddb.model.Key;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_19_Action_II {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;

    Actions action;

    String osName = System.getProperty("os.name");
    Keys keys;
    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        action = new Actions(driver);

        if (osName.startsWith("Window")){
            keys = Keys.CONTROL;
        } else {
            keys = Keys.COMMAND;
        }

        keys = osName.startsWith("Window") ? Keys.CONTROL : Keys.COMMAND;
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_Click_And_Hold_Block() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
        Assert.assertEquals(allNumber.size(),20);

//        driver.findElement(By.cssSelector());
        action.clickAndHold(allNumber.get(0)) // click số 1
                .moveToElement(allNumber.get(15)) // di chuột tới 4
                .release() // nhả chuột trái ra - kết thúc sự kiện click-hold
                .perform(); // thực thi câu lệnh trên - nếu không có sẽ không thực thi
        Thread.sleep(2000);

        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(allNumberSelected.size(),16);
    }

    @Test
    public void TC_02_Click_And_Hold_Block_Random() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
        Assert.assertEquals(allNumber.size(),20);

        //Nhấn ctrl (chưa nhả)
        action.keyDown(keys).perform();

        action.click(allNumber.get(0))
                .click(allNumber.get(8))
                .click(allNumber.get(12))
                .click(allNumber.get(7))
                .pause(Duration.ofSeconds(3))
                .perform();



        //Nhả phím ctrl
        action.keyUp(keys).perform();

//        Thread.sleep(2000);

        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(allNumberSelected.size(),4);
    }

    @Test
    public void TC_03_Double_Click(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.manage().window().maximize();

        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(),"Hello Automation Guys!");
    }

    // 3- Clean: Delete data test/account/close browser/...
    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }

}