package webdriver.part3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_26_Wait_FindElement {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        //Total time tìm kiếm là 13s
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(13));
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_FindElement(){
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        //1- Nếu tìm thấy duy nhất 1 element
        //Khi tìm thấy sẽ dừng việc tìm mà không cần chờ hết 13s
        driver.findElement(By.cssSelector("input#FirstName"));

        //2- Tìm thấy nhiều hơn 1 element
        driver.findElement(By.cssSelector("input[type='text']"));

        try{
            //3- Không tìm thấy element nào
            // Ban đầu fineElement không tìm thấy
            // Tìm lại mà thấy Element thf không cần chờ hết tổng time còn lại
            // Tìm lại và hết 13s không thấy sẽ đánh fail
            //Show lỗi: NoSuchElementExption
            driver.findElement(By.cssSelector("input#RememberMe"));
        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }


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