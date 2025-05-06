package webdriver.part3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_09_WebBrowser_Exercise2 {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_Displayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement emailTextboxCheck = driver.findElement(By.cssSelector("input#mail"));

        if (emailTextboxCheck.isDisplayed()) {
            System.out.println("Email textbox is displayed");
            emailTextboxCheck.sendKeys("phuong@gmail.com");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        } else {
            System.out.println("Email textbox is not displayed");
        }

        WebElement AgeRadioButtonCheck = driver.findElement(By.cssSelector("input#under_18"));
        if (AgeRadioButtonCheck.isDisplayed()) {
            System.out.println("Age radio button is displayed");
            AgeRadioButtonCheck.click();
        } else {
            System.out.println("Age radio button is not displayed");
        }

        WebElement EducationTextareaCheck = driver.findElement(By.cssSelector("textarea#edu"));
        if (EducationTextareaCheck.isDisplayed()) {
            System.out.println("Education text area is displayed");
            EducationTextareaCheck.sendKeys("Automation Selenium");
        } else {
            System.out.println("Education text area is not displayed");
        }

        WebElement User5Text = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
        if (User5Text.isDisplayed()) {
            System.out.println("User 05 text is displayed");
        } else {
            System.out.println("User 05 text is not displayed");
        }

    }

    @Test
    public void TC_02_Enable() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement emailTextboxCheck = driver.findElement(By.cssSelector("input#mail"));

        if (emailTextboxCheck.isEnabled()) {
            System.out.println("Email textbox is enable");
        } else {
            System.out.println("Email textbox is disable");
        }

        WebElement AgeRadioButtonCheck = driver.findElement(By.cssSelector("input#under_18"));
        if (AgeRadioButtonCheck.isEnabled()) {
            System.out.println("Age radio button is enable");
            AgeRadioButtonCheck.click();
        } else {
            System.out.println("Age radio button is disable");
        }

        WebElement passwordTextbox = driver.findElement(By.xpath("//input[@id='disable_password']"));
        if (passwordTextbox.isEnabled()) {
            System.out.println("Password textbox is enable");
        } else {
            System.out.println("Password textbox is disable");
        }

    }

    @Test
    public void TC_03_Selected() {
//Kiểm tra element đã chọn thành công
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement AgeRadioButtonCheck = driver.findElement(By.cssSelector("input#under_18"));

        if (AgeRadioButtonCheck.isSelected()) {
            System.out.println("Age radio button is selected");
        } else {
            System.out.println("Age radio button is de-selected");
        }

        WebElement developmentCheckbox = driver.findElement(By.xpath("//input[@id='development']"));

        if (developmentCheckbox.isSelected()) {
            System.out.println("Development checkbox is selected");
        } else {
            System.out.println("Development checkbox is de-selected");
        }



        AgeRadioButtonCheck.click();
        developmentCheckbox.click();

        if (AgeRadioButtonCheck.isSelected()) {
            System.out.println("Age radio button is selected");
        } else {
            System.out.println("Age radio button is de-selected");
        }

        if (developmentCheckbox.isSelected()) {
            System.out.println("Development checkbox is selected");
        } else {
            System.out.println("Development checkbox is de-selected");
        }

    }

    @Test
    public void TC_04_MailChimp_Register_Validate() {
        driver.get("https://login.mailchimp.com/signup/");

    }

    // 3- Clean: Delete data test/account/close browser/...
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}