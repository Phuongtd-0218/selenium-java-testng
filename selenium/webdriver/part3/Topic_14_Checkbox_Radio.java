package webdriver.part3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Checkbox_Radio {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_Telerik() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        //Veriry checkbox/ radio is enabled/disabled
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input")).isEnabled());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Towbar preparation']/preceding-sibling::span/input")).isEnabled());

        // Verify checkbox/radio is selected/deselected
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Towbar preparation']/preceding-sibling::span/input")).isSelected());

        // Select to "Dual-zone air conditioning" checkbox
        By dualZoneBy = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");

        // Nếu chưa chọn sẽ click
        if (!driver.findElement(dualZoneBy).isSelected()){
            driver.findElement(dualZoneBy).click();
        }
        Assert.assertTrue(driver.findElement(dualZoneBy).isSelected());

        //Bỏ chọn
        if (driver.findElement(dualZoneBy).isSelected()){
            driver.findElement(dualZoneBy).click();
        }
        Assert.assertFalse(driver.findElement(dualZoneBy).isSelected());

//        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
//        WebElement radioButtonEngine1 = driver.findElement(By.cssSelector("input#engine1"));
//
//        if (!radioButtonEngine1.isSelected()) {
//            radioButtonEngine1.click();
//        } else {
//            Assert.assertTrue(radioButtonEngine1.isSelected());
//            System.out.println("Đã được chọn");
//        }
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