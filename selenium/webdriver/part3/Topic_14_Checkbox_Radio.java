package webdriver.part3;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_14_Checkbox_Radio {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;

    WebDriverWait expliciWait;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        expliciWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();

//        driver.manage().window().setSize(new Dimension(1366,768));
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_Telerik() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        //Trường hợp size màn hình hiển thị nhở hơn 1080*1920
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0,300)", "");

        //Chờ cho đến khi hiển thị icon checkbox lên hết mới bắt đầu chạy test case
        expliciWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input.k-checkbox")));


        //Veriry checkbox/ radio is enabled/disabled
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input")).isEnabled());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Towbar preparation']/preceding-sibling::span/input")).isEnabled());

        // Verify checkbox/radio is selected/deselected
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Towbar preparation']/preceding-sibling::span/input")).isSelected());

        // Select to "Dual-zone air conditioning" checkbox
        By dualZoneBy = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");

        // Nếu chưa chọn sẽ click
        if (!driver.findElement(dualZoneBy).isSelected()) {
            driver.findElement(dualZoneBy).click();
        }
        Assert.assertTrue(driver.findElement(dualZoneBy).isSelected());

        //Bỏ chọn
        if (driver.findElement(dualZoneBy).isSelected()) {
            driver.findElement(dualZoneBy).click();
        }
        Assert.assertFalse(driver.findElement(dualZoneBy).isSelected());

        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");
        expliciWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input.k-radio")));

        By twoPetrolBy = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");

        if (!driver.findElement(twoPetrolBy).isSelected()) {
            driver.findElement(twoPetrolBy).click();
            Assert.assertTrue(driver.findElement(twoPetrolBy).isSelected());
        } else if (driver.findElement(twoPetrolBy).isSelected()) {
            driver.findElement(twoPetrolBy).click();
            Assert.assertFalse(driver.findElement(twoPetrolBy).isSelected());
        }
    }

    @Test
    public void TC_02_Multiple() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        driver.manage().window().maximize();
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,1000)");

        expliciWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input.form-checkbox")));

        List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("input.form-checkbox"));

        // Select all checkboxes
        for (WebElement checkbox : allCheckboxes){
            checkbox.click();
            // Verify all checkboxes selected
            Assert.assertTrue(checkbox.isSelected());
        }

        // Deselect all checkboxes
        for (WebElement checkbox : allCheckboxes){
            checkbox.click();
            // Verify all checkboxes deselected
            Assert.assertFalse(checkbox.isSelected());
        }

//        driver.findElement(By.xpath("//label[text()=' Tuberculosis ']/preceding-sibling::input[@value='Tuberculosis']")).click();
//        Assert.assertTrue(driver.findElement(By.xpath("//label[text()=' Tuberculosis ']/preceding-sibling::input[@value='Tuberculosis']")).isSelected());
        // Selecet 1 in all + veryfy

        for (WebElement checkbox : allCheckboxes){
            if (!checkbox.isSelected() && checkbox.getAttribute("value").equals("Tuberculosis")){
                checkbox.click();
            }
        }

    }

    // 3- Clean: Delete data test/account/close browser/...
    @AfterClass
    public void cleanBrowser() {
//        driver.quit();
    }

}