package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Alway_Run {

    WebDriver driver;

    //Arrange
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.get("https://www.fahasa.com/");

        //Go to login Page

        //Login success
        // Login no success => fail

        Assert.assertTrue(false);
    }

    @Test
    public void TC_01(){
        System.out.println("run TC_01");

    }

    @Test
    public void TC_02(){
        System.out.println("run TC_02");

    }

    @AfterClass(alwaysRun = true) //Cho dù các testcase trước false thì tc này vẫn chạy, không nên đưa vào trong TC
    public void afterClass(){
        driver.quit();

    }
}
