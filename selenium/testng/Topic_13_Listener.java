package testng;

import listener.ScreenShotListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Random;

@Listeners(ScreenShotListener.class)
public class Topic_13_Listener {
    public WebDriver getDriver() {
        return driver;
    }

    WebDriver driver;
    String employeeID;
    String firstName = "Phuong";
    String lastName = "Duy";

    String password = "Testing111###";
    String domainUrl = "https://opensource-demo";


    @BeforeClass
    @Parameters({"browser", "server"}) //Suite không dùng với parameters
    public void beforeClass(@Optional("Firefox") String browserName, String serverName) {
    if (serverName.equalsIgnoreCase("Dev")){
        domainUrl = "https://dev";
    }
    else if (serverName.equalsIgnoreCase("Testing")){
        domainUrl = "https://testing";
    }
    else if (serverName.equalsIgnoreCase("Staging")){
        domainUrl = "https://staging";
    }
    else if (serverName.equalsIgnoreCase("Production")){
        domainUrl = "https://opensource-demo";
    }
    else {
        throw new RuntimeException("Browser name is no invalid");
    }

    switch (browserName){
        case "Chorme":
            driver = new ChromeDriver();
            break;
        case "Edge":
            driver = new EdgeDriver();
            break;
        case "Firefox":
            driver = new FirefoxDriver();
            break;
        default:
            new RuntimeException("Browser name is no invalid");
    }

        // Khởi tạo browser
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }


    // Call dataProvider by call name of DataProvider
    @Test(invocationCount = 2)
    public void TC_01_OrangeHRM_Multiple_Browser() throws InterruptedException {

        driver.get(domainUrl+".orangehrmlive.com/");
        String emailAddress = "Phuong" + new Random().nextInt() + "@gmail.com";

        // Login
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        // Wait for all Loading Icon disappear
        Assert.assertTrue(isLoadingIconDisappear());

        // Verify Dashboard page displayed
        Assert.assertTrue(driver.findElement(By.xpath("//h6[text()='Dashboard']")).isDisplayed());

        // Click to PIM link
        driver.findElement(By.xpath("//span[text()='PIM']/parent::a")).click();
        Assert.assertTrue(isLoadingIconDisappear());

        // Verify PIM page displayed
        Assert.assertTrue(driver.findElement(By.xpath("//h6[text()='PIM']")).isDisplayed());

        // Add Employee
        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
        Assert.assertTrue(isLoadingIconDisappear());

        // Create new Employee
        driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input[name='lastName']")).sendKeys(lastName);

        employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomProperty("value");

        driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div//span")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);

        driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
        Thread.sleep(2000);

        // Verify Success Message displayed
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Successfully Saved']")).isDisplayed());

        // Loading icon at Add Employee page
        Assert.assertTrue(isLoadingIconDisappear());

        // Loading Personal Details page
        Assert.assertTrue(isLoadingIconDisappear());

        // Personal Details page
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getDomProperty("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getDomProperty("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomProperty("value"), employeeID);

        // Logout
        driver.findElement(By.cssSelector("li.oxd-userdropdown")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
    }

    private Boolean isLoadingIconDisappear() {
        // Explicit: áp dụng cho các trạng thái của element (hiển thị/ ko hiển thị (ẩn)/ clickable/ selectable/..)
        return new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions
                .invisibilityOfAllElements(driver.findElements(By.cssSelector("div.oxd-loading-spinner"))));
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }
}
