package webdriver.part3;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_WebElement_Commands {

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;

    WebElement element;

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_WebElement() {
        //Dùng 1 lần
        driver.findElement(By.xpath("")).click();

        //
        element = driver.findElement(By.xpath(""));

        //Click vào các element dạng: button, checkbox, radio, link, image, icon..
        element.click();

        //Nhập liệu vào các element dạng: textbox/ textarea/ dropdown(edit)
        element.clear();//Xóa dữ liệu trước khi sendkey
        element.sendKeys("xx@gmail.com");
        element.sendKeys(Keys.ENTER);

        driver.findElement(By.cssSelector("div.login-page")).findElement(By.cssSelector("div.customer-blocks"))
                .findElement(By.id("Email"));

        //Tác dụng với form (Signup/ Login/ Search...)
        // thẻ form
        element.submit(); // thực tế ít dụng - như ấn Enter sau khi nhập

        // Áp dụng cho tất cả các loại element
        //Kiểm tra 1 element có hiển thị hay không
        // Size > 0: width/height > 0
        // Nhìn thấy/ thao tác được
        element.isDisplayed();

        Assert.assertTrue(element.isDisplayed());
        Assert.assertFalse(element.isDisplayed());

        // Áp dụng duy nhất cho 3 loại: checkbox/ radio/ dropdown(default)
        //Kiểm tra xem 1 element đã được chọn hay chưa
        element.isSelected();

        //Áp dụng cho tất cả các loại
        // Kiểm tra xem element có bị disable hay không (read-only)
        element.isEnabled();


        element.getCssValue("background-color");

        element.getText();
    }

}