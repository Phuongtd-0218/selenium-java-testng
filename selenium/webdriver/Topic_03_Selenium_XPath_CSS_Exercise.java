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

//    WebElement validateFirstname = driver.findElement(By.id("txtFirstname-error"));
//    WebElement validateEmail = driver.findElement(By.id("txtEmail-error"));
//    WebElement validateCheckEmail = driver.findElement(By.id("txtCEmail-error"));
//    WebElement validatePassword = driver.findElement(By.id("txtPassword-error"));
//    WebElement validateCheckPassword = driver.findElement(By.id("txtCPassword-error"));
//    WebElement validatePhoneNumber = driver.findElement(By.id("txtPhone-error"));
//
//    WebElement clickRegisterButton = driver.findElement(By.xpath("//button[@type='submit']"));
//
//    WebElement firtNameTxt = driver.findElement(By.name("txtFirstname"));
//    WebElement emailTxt = driver.findElement(By.name("txtFirstname"));
//    WebElement checkEmailTxt = driver.findElement(By.name("txtFirstname"));
//    WebElement passwordTxt = driver.findElement(By.name("txtFirstname"));
//    WebElement checkPasswordTxt = driver.findElement(By.name("txtFirstname"));
//    WebElement phoneNumberTxt = driver.findElement(By.name("txtFirstname"));


    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();
//        driver.get("https://live.techpanda.org");
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_Register_Emplty_Data() throws InterruptedException {
        //Arrange: set up the test enviroment
        initialBrowser();

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
        Thread.sleep(5000);
    }

    @Test
    public void TC_02_Register_Invalid_Email() throws InterruptedException {
        //Arrange
        initialBrowser();
        WebElement clickRegisterButton = driver.findElement(By.xpath("//button[@type='submit']"));

        WebElement firtNameTxt = driver.findElement(By.name("txtFirstname"));
        firtNameTxt.sendKeys("Trinh Duy Phuong");
        WebElement emailTxt = driver.findElement(By.name("txtEmail"));
        emailTxt.sendKeys("phuong@phuong@");
        WebElement checkEmailTxt = driver.findElement(By.name("txtCEmail"));
        checkEmailTxt.sendKeys("phuong@phuong@");
        WebElement passwordTxt = driver.findElement(By.name("txtPassword"));
        passwordTxt.sendKeys("12345678");
        WebElement checkPasswordTxt = driver.findElement(By.name("txtCPassword"));
        checkPasswordTxt.sendKeys("12345678");
        WebElement phoneNumberTxt = driver.findElement(By.name("txtPhone"));
        phoneNumberTxt.sendKeys("0987876903");

        //Action
        driver.findElement(By.id("chkRight")).click();
        clickRegisterButton.click();
        WebElement validateEmail = driver.findElement(By.id("txtEmail-error"));
        WebElement validateCheckEmail = driver.findElement(By.id("txtCEmail-error"));

        //Assert
        Assert.assertEquals(validateEmail.getText(),"Vui lòng nhập email hợp lệ");
        System.out.println("success");
        Assert.assertEquals(validateCheckEmail.getText(),"Email nhập lại không đúng");
        System.out.println("success");
        Thread.sleep(5000);
    }

    @Test
    public void TC_03_Register_Incorrect_Confirm_Email(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


    }

    @Test
    public void TC_04_Register_Invalid_Password(){
        //Arrange
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Action

        //Assert

    }

    @Test
    public void TC_05_Incorrect_Confirm_Password(){
        //Arrange
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Action

        //Assert

    }

    @Test
    public void TC_06_Register_Invalid_PhoneNumber(){
        //Arrange
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Action

        //Assert

    }


    // 3- Clean: Delete data test/account/close browser/...
    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }

}