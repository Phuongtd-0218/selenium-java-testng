package webdriver.part3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Fixed_Popup {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();

        driver.get("https://demo.nopcommerce.com");
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_NgoaiNgu24h(){

        //Link trang test

        //Tạo 1 biến vị trí popup

        //Kiểm tra hiển thị

    }

    @Test
    public void TC_02_(){


    }

    @Test
    public void TC_03_Tiki_(){

        // Kiểm ta TH element không còn trong HTML

    }

    // 3- Clean: Delete data test/account/close browser/...
    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }

}