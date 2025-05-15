package webdriver.part3;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Custom_Checkbox {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;
    JavascriptExecutor jsExcutor;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_Ubuntu() {
        driver.get("https://ubuntu.com/login");
        jsExcutor = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");

//        By newUserRadio = By.cssSelector("input#id_new_user");

        //1- thẻ input: Dùng để click -> fail
        // driver.findElement(newUserRadio).click();

//        Assert.assertTrue(driver.findElement(newUserRadio).isSelected());
//
//        By newUserRadio = By.cssSelector("label.new-user");
        //2- Dùng thẻ khác input để click -> Pass
//        driver.findElement(newUserRadio).click();
//        Assert.assertTrue(driver.findElement(newUserRadio).isSelected());

        //3- Dùng 1 thẻ khác input để click -> pass
        //Dùng thẻ input này để verify -> pass
//        By newUserRadioLabel = By.cssSelector("label.new-user");
//        By newUserRadioInput = By.cssSelector("input#id_new_user");
//
//        driver.findElement(newRadioLabel).click();
//        Assert.assertTrue(driver.findElement(newRadioInput).isSelected());
        //Thỏa mãn điều kiện

        //4-Dung duy nahả thẻ input để click/Verify dùng JS Excutor
        By newUserRadioInput = By.cssSelector("input#id_new_user");

        jsExcutor.executeScript("arguments[0].click();", driver.findElement(newUserRadioInput));
        Assert.assertTrue(driver.findElement(newUserRadioInput).isSelected());

        By termCheckbox = By.cssSelector("input#id_accept_tos");

        jsExcutor.executeScript("arguments[0].click();", driver.findElement(termCheckbox));
        Assert.assertTrue(driver.findElement(termCheckbox).isSelected());

    }

    @Test
    public void TC_02_Docs() {


    }

    // 3- Clean: Delete data test/account/close browser/...
    @AfterClass
    public void cleanBrowser() {
//        driver.quit();
    }

}