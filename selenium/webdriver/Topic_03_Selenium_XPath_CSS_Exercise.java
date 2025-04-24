package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_03_Selenium_XPath_CSS_Exercise {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();
        driver.get("https://live.techpanda.org");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_Register_Emplty_Data(){
        //Arrange: set up the test enviroment
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Act: Execute the code to test
        WebElement clickRegisterButton = driver.findElement(By.xpath("//button[@type='submit']"));
        clickRegisterButton.click();
        WebElement validateFirstname = driver.findElement(By.id("txtFirstname-error"));
        WebElement validateEmail = driver.findElement(By.id("txtEmail-error"));
        WebElement validateCheckEmail = driver.findElement(By.id("txtCEmail-error"));
        WebElement validatePassword = driver.findElement(By.id("txtPassword-error"));
        WebElement validateCheckPassword = driver.findElement(By.id("txtCPassword-error"));
        WebElement validatePhoneNumber = driver.findElement(By.id("txtPhone-error"));

        //Assert: verify the results
        Assert.assertEquals(validateFirstname.getText(),"Vui lòng nhập họ tên");
        System.out.println("success");
        Assert.assertEquals(validateEmail.getText(),"Vui lòng nhập email");
        System.out.println("success");
        Assert.assertEquals(validateCheckEmail.getText(),"Vui lòng nhập lại địa chỉ email");
        System.out.println("success");
        Assert.assertEquals(validatePassword.getText(),"Vui lòng nhập mật khẩu");
        System.out.println("success");
        Assert.assertEquals(validateCheckPassword.getText(),"Vui lòng nhập lại mật khẩu");
        System.out.println("success");
        Assert.assertEquals(validatePhoneNumber.getText(),"Vui lòng nhập số điện thoại.");
        System.out.println("success");

        System.out.println("success full");
    }

    @Test
    public void TC_02_(){


    }

    // 3- Clean: Delete data test/account/close browser/...
    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }

}