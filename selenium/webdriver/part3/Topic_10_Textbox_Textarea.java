package webdriver.part3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_10_Textbox_Textarea {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;

    Random random;
    String firstName, lastName, emailAddress, password, fullName;

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();
        random = new Random();

        firstName="Phuong";
        lastName="Trinh";
        emailAddress = firstName + random.nextInt(999) + "gmail.com";
        password="123456";
        fullName = firstName + " " + lastName;
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_TechPanda(){
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        driver.findElement(By.cssSelector("a[title='Create an Account'] span")).click();

        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);

        driver.findElement(By.cssSelector("button[title='Register']")).click();

        String gettext = driver.findElement(By.cssSelector("li.success-msg span")).getText();
        //Tuyệt đối
        Assert.assertEquals(gettext,"Thank you for registering with Main Website Store.");

        String contactInforText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p")).getText();

        System.out.println(contactInforText);

        // Verify  tương đối
        Assert.assertTrue(contactInforText.contains(fullName) && contactInforText.contains(emailAddress)); //Fullname & Email

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