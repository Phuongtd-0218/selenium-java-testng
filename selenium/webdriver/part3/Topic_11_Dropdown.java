package webdriver.part3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Dropdown {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;
    Select select;


    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();

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

        Object name ;
        name = "name";
    }

    @Test
    public void TC_02_(){


    }

    // 3- Clean: Delete data test/account/close browser/...
//    @AfterClass
//    public void cleanBrowser(){
//        driver.quit();
//    }

}