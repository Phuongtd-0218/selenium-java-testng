package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class Topic_01_Annotation {

    WebDriver driver;

    @BeforeMethod
    public void beforMethod(){
        System.out.println("run before method");

    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("run after Method");

    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("run before Class");

    }

    @AfterClass
    public void afterClass(){
        System.out.println("run after Class");

    }

    @BeforeTest
    public  void beforeTest(){
        System.out.println("run before Test");

    }

    @AfterTest
    public void afterTest(){
        System.out.println("run after Test");

    }
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("run before Suite");

    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("run after Suite");

    }

    @Test
    public void TC_01(){
        System.out.println("run TC_01");

    }

    @Test
    public void TC_02(){
        System.out.println("run TC_02");

    }
}
