package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_05_Run_On_Browser_3x {

    WebDriver driver;

    String projectPath = System.getProperty("user.dir"); //Lấy đường dẫn tương đối


    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_Run_On_Firefox(){
        System.getProperty("webdriver.gecko.driver"+ projectPath+"\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();

        driver.get("https://demo.nopcommerce.com/");

        driver.quit();
    }

    @Test
    public void TC_02_Run_On_Chorme(){
        System.getProperty("webdriver.gecko.driver"+ projectPath+"\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.get("https://demo.nopcommerce.com/");

        driver.quit();

    }

    @Test
    public void TC_02_Run_On_MSEdge(){
        System.getProperty("webdriver.gecko.driver"+ projectPath+"\\browserDrivers\\msedgedriver.exe");
        driver = new EdgeDriver();

        driver.get("https://demo.nopcommerce.com/");

        driver.quit();

    }

}