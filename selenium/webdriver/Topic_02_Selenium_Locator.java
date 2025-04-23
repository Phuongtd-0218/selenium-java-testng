package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();

        //Mở màn lên đến màn login
        driver.get("https://demo.nopcommerce.com/register");
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_ID() throws InterruptedException {
        driver.findElement(By.id("small-searchterms")).sendKeys("Macbook");
        Thread.sleep(3000);

        driver.findElement(By.id("FirstName")).sendKeys("firtName");
        Thread.sleep(3000);
//        // Tương tác lên Email Address textbox
//            /*<input class="email" autofocus="" type="email" data-val="true" data-val-regex="Wrong email"
//       data-val-required="Please enter your email" id="Email" name="Email">*/
//        // HTML Source Code
//        // Thẻ - thuộc tính - giá trị thuộc tính
//        //Tagname - Attribute - Value
//
//
//        //XPath: //tagname[@attribute = 'value']
//        //Css: tagname[attribute = 'value']
//
//        // Tương tác lên Email address textbox
//        // 8 loại locator để tìm kiếm Email Address
//
//
//        // Sau dấu chấm sẽ gọi hàm
//
//        //Tìm 1 element
//        driver.findElement(By.id(""));
//
//        // 1- Thao tác lên luôn(dùng 1 lần)
//        driver.findElement(By.id("")).click();
//
//        // 2- Lưu dữ liệu lại(dùng nhiều lần)
//        // Dùng nhiều lần thì nên khai báo
//        WebElement emailTextbox = driver.findElement(By.id(""));
//        emailTextbox.click();
//        emailTextbox.sendKeys("");
//        emailTextbox.getTagName();
//
//        //Tìm nhiều element giống nhau
//
//        List<WebElement> textboxes = driver.findElements(By.cssSelector(""));
    }

    @Test
    public void TC_02_Class() {
        // Không lấy hết giá trị nếu có khoảng trắng
        driver.findElement(By.className("register-next-step-button")).click();
    }

    @Test
    public void TC_03_Name() {
        driver.findElement(By.name("Company"));

    }

    @Test
    public void TC_04_Link_Text() {
        //Chỉ làm việc với element là Link
        // Thẻ a và có thuộc tính href
        driver.findElement(By.linkText("Sitemap"));
        driver.findElement(By.linkText("Log in"));
    }

    @Test
    public void TC_05_Partial_Link_Text() {
        //Có thể lấy hết toàn bộ text hoặc một phần (hay dùng)
        driver.findElement(By.partialLinkText("Sitemap"));
        driver.findElement(By.partialLinkText("Electronics"));
    }

    @Test
    public void TC_06_Tagname() {
        // Tên thẻ (HTML)
        // Tìm các element giống nhau (thẻ của component giống nhau)

        driver.findElements(By.tagName("input"));
        driver.findElements(By.tagName("label"));
    }

    @Test
    public void TC_07_Css() {
        //id
        driver.findElement(By.cssSelector("input#Company"));
        driver.findElement(By.cssSelector("#Company"));
        driver.findElement(By.cssSelector("input[id='Company']"));

        //class
        driver.findElement(By.cssSelector("input.search-box-text"));
        driver.findElement(By.cssSelector("input[class='search-box-text ui-autocomplete-input']"));

        //name
        driver.findElement(By.cssSelector("input[name='Email']"));
        driver.findElement(By.cssSelector("input[name='LastName']"));

        //link-text
        driver.findElement(By.cssSelector("a[href='/register?returnUrl=%2Fregister']"));
        driver.findElement(By.cssSelector("a[href='/news']"));


        //partial-link-text
        driver.findElement(By.cssSelector("a[href*='/register?']"));
        driver.findElement(By.cssSelector("a[href*='/login?']"));


        //tagname
        driver.findElement(By.cssSelector("a"));
        driver.findElement(By.cssSelector("input"));
        driver.findElement(By.cssSelector("button"));

    }

    @Test
    public void TC_08_XPath() {
        //id
        driver.findElement(By.xpath("//input[@id='small-searchterms']"));
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']"));
        driver.findElement(By.xpath("//input[contains(@id,'Confirm')]"));

        //class
        driver.findElement(By.xpath("//input[@class='newsletter-subscribe-text']"));
        driver.findElement(By.xpath("//input[contains(@class,'newsletter')]"));

        //name
        driver.findElement(By.xpath("//button[@name='register-button']"));
        driver.findElement(By.xpath("//input[contains(@name,'Password')]"));

        //link-text
        driver.findElement(By.xpath("//a[text()='Apply for vendor account']"));
        driver.findElement(By.xpath("//a[text()='Compare products list']"));


        //partial-link-text
        driver.findElement(By.xpath("//a[contains(text(),'Compare')]"));
        driver.findElement(By.xpath("//a[contains(text(),'About')]"));

        //tagname
        driver.findElement(By.xpath("//input"));
        driver.findElement(By.xpath("//a"));


    }

    // 3- Clean: Delete data test/account/close browser/...
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}