package webdriver.part3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class Topic_11_Default_Dropdown {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;
    Select select;

    Random random;
//    By emailTextboxBy = By.cssSelector("input.email");
    String firstName,lastName,email,password,company;

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();

        random = new Random();

        firstName = "John";
        lastName = "Michael";
        email = "John" + random.nextInt(99) + "@gmail.com";
        password = "123456";
        company = "Facebook";
        //Nhận driver là tham số sẽ khởi tạo ở đây
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_Facebook_SignUp(){
        driver.get("https://www.facebook.com/reg");
        //Xổ dropdown list ra
        select = new Select(driver.findElement(By.cssSelector("select#day")));
//        //1-index
//        select.selectByIndex(20);
//        //Index thay đổi vị trí
//        //Đọc code không biết chọn tỉnh nào => chạy fail => mất thời gian tìm giá trị tương ứng
//
//        //2-Value
//        select.selectByValue("998");
//        select.selectByValue("999");
//        //Option không bắt buộc phải có attribute value
//        //Đọc code không biết tỉnh nào => chạy fail => mất thời gian tìm giá trị tương ứng
//        //Thêm 1 số bước để tìm đúng giá trị
//
//        //3-Text
//        select.selectByVisibleText("Thành phố Hà Nội");
//        //Giống như end-user chọn
//        //Không bị trùng dữ liệu trên các môi trường
//        //Không thay đổi text khi đổi vị trí
//        //Chạy fail => vẫn tìm được giá trị để manual retest=> ít mất thời gian hơn

        //Chọn 1 item
        select.selectByVisibleText("25");

        //Verìy xem đã chọn thành công hay chưa
        Assert.assertEquals( select.getFirstSelectedOption().getText(),"25");

        //Verìy dropdown có phải mà multible không
        // Nếu là multible -> trả về là true
        // Nếu là single -> trả về là false

//        Assert.assertFalse(select.isMultiple());
//
//        //Verify tổng số lượng item
//        Assert.assertEquals(select.getOptions().size(),"31");

        select = new Select(driver.findElement(By.cssSelector("select#month")));
        select.selectByVisibleText("Jun");
        Assert.assertEquals( select.getFirstSelectedOption().getText(),"Jun");

        select = new Select(driver.findElement(By.cssSelector("select#year")));
        select.selectByVisibleText("2024");
        Assert.assertEquals( select.getFirstSelectedOption().getText(),"2024");
    }

    @Test
    public void TC_02_NopCpmmerce(){
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.cssSelector("a.ico-register")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);

//        new Select(driver.findElement(By.cssSelector(""))).selectByVisibleText("");

        //Register
        driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#Company")).sendKeys(company);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);
        driver.findElement(By.cssSelector("button#register-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");

        driver.findElement(By.cssSelector("a.ico-login")).click();
        //Login
        driver.findElement(By.cssSelector("input.email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.login-button")).click();

        //My account
        driver.findElement(By.cssSelector("a.ico-account")).click();
//        driver.findElement(By.cssSelector("input.email")).sendKeys(email);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"),firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"),lastName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"),email);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"),company);
    }

    @Test
    public void TC_03_Rode(){
    driver.get("https://rode.com/en/support/where-to-buy");
    new Select(driver.findElement(By.cssSelector("select#country"))).selectByVisibleText("Vietnam");
    driver.findElement(By.cssSelector("input#map_search_query")).sendKeys("HO CHI MINH");
    driver.findElement(By.cssSelector("button.btn.btn-default")).click();

        List<WebElement> dealers = driver.findElements(By.xpath("//h3[text()='Dealers']/following-sibling::div//h4"));
        Assert.assertEquals(dealers.size(),16);

        for (WebElement element: dealers){
            System.out.println(element.getText());
        }
    }

    // 3- Clean: Delete data test/account/close browser/...
//    @AfterClass
//    public void cleanBrowser(){
//        driver.quit();
//    }

}