package webdriver.part2;

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_Register_Emplty_Data() {
        //Arrange: set up the test enviroment
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        //Act: Execute the code to test
        WebElement clickRegisterButton = driver.findElement(By.xpath("//button[@type='submit']"));
        clickRegisterButton.click();

        //Assert: verify the results
        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
        System.out.println("success");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
        System.out.println("success");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
        System.out.println("success");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
        System.out.println("success");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
        System.out.println("success");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");
        System.out.println("success");

        System.out.println("success full");
    }

    @Test
    public void TC_02_Register_Invalid_Email() throws InterruptedException {
        //Arrange
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        //Action
        driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("phuong@phuong@phuong");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("phuong@phuong@phuong");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //Assert
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
        System.out.println("success");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
        System.out.println("success");
        Thread.sleep(5000);
    }

    @Test
    public void TC_03_Register_Incorrect_Confirm_Email(){
        //Arrange
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        //Action
        driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("phuongtd@gmail.com");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("phuongtd@gmail.net");

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //Assert
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
        System.out.println("success");
    }

    @Test
    public void TC_04_Register_Invalid_Password(){
        //Arrange
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        //Action
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("1234");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("1234");

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //Assert

        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void TC_05_Incorrect_Confirm_Password(){
        //Arrange
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        //Action
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("1234567");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("1234568");

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //Assert

        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu bạn nhập không khớp");
    }

    @Test
    public void TC_06_Register_Invalid_PhoneNumber(){
        //Arrange
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        WebElement inputPhoneNUmber = driver.findElement(By.xpath("//input[@id='txtPhone']"));

        //Action - //Assert
        //<10 number
        inputPhoneNUmber.sendKeys("098764");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");
        inputPhoneNUmber.clear();

        //>11 number
        inputPhoneNUmber.sendKeys("098764893738");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");
        inputPhoneNUmber.clear();

        // start-with: 09.03,....
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("98764");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
        System.out.println("success");
    }


    // 3- Clean: Delete data test/account/close browser/...
    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }

}