package webdriver.part3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_12_Custom_Dropdown {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_(){
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        //Hành vi thể thiện thao tác lên dropdown
        // 1- Chờ cho dropdown có thể thao tác lên được (clickable)

        // 2- Click vào element nào để nó xổ ra cái dropdown ra
        // 3- Chờ cho tất cả item load ra thành công
        // 4- Tìm item mong muốn
        // 5- Click lên item đó



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