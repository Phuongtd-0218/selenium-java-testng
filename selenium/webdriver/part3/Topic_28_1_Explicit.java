package webdriver.part3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class Topic_28_1_Explicit {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;

    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

//        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(300));
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_() {
        driver.get("https://www.youtube.com/");
        String url = "https://www.youtube.com/";
        String containsUrl = "youtube.com";
        String title = driver.getTitle();

        By byElement = By.cssSelector("");
        WebElement webElement = driver.findElement(byElement);

        //Wait cho element không hiển thị không còn trong HTML(Trước đó là có tồn tại)
        explicitWait.until(ExpectedConditions.stalenessOf(webElement));

        //Wait cho element không hiển thị(còn hoặc không còn trong HTML)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byElement));

        //Wait cho element được hiển thị(phải có trong HTML và có trên UI)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byElement));

        //Wait cho element được phép click(button/link/radio/checkbox/..)
        explicitWait.until(ExpectedConditions.elementToBeClickable(byElement));

        //Wait cho URL của page tuyệt đối
        explicitWait.until(ExpectedConditions.urlToBe(url));

        //Wait cho URL của page tương đối
        explicitWait.until(ExpectedConditions.urlContains(containsUrl));

        //Wait cho URL của page thỏa mãn biểu thức (Regex)
        explicitWait.until(ExpectedConditions.urlMatches("*$&#^..."));

        //Wait cho JS return value có type là String
        explicitWait.until(ExpectedConditions.jsReturnsValue("return arguments[0].validationMessage;"));

        //Wait cho Alert xuất hiện trong HTML
        explicitWait.until(ExpectedConditions.alertIsPresent());

        //Wait cho Page Title tuyệt đối
        explicitWait.until(ExpectedConditions.titleIs(title));

        //Wait kết hợp nhiều điều kiện (AND)
        explicitWait.until(ExpectedConditions.and(
                ExpectedConditions.alertIsPresent(), ExpectedConditions.titleIs(title)));

        //Wait thỏa mãn 1 trong 2 điều kiện (OR)
        explicitWait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains(containsUrl), ExpectedConditions.titleIs(title)));

        //Wait cho element có xuất hiện trong HTML (không cần hiển thị trên UI)
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(byElement));

        //Wait cho 1 element có thuộc tính chứa 1 giá trị nào đó
        explicitWait.until(ExpectedConditions.attributeContains(byElement, "class", "email"));

        //Wait cho 1 element có thuộc tính không được rỗng
        explicitWait.until(ExpectedConditions.attributeToBeNotEmpty(webElement, "class"));

        //Wait cho 1 element có thuộc tính ở trong DOM attribute bằng giá trị nào đó
        explicitWait.until(ExpectedConditions.domAttributeToBe(webElement,"baseUR", ""));

        //Wait cho 1 element có thuộc tính ở trong DOM properties bằng giá trị nào đó
        explicitWait.until(ExpectedConditions.domPropertyToBe(webElement,"baseUR", ""));

        //Wait cho element được chọn hay không (Checkbox, Radio, Dropdown Item)
        explicitWait.until(ExpectedConditions.elementToBeSelected(byElement));

        //Wait cho element đã được chọn thành công
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(byElement, true));

        //Wait cho element chưa được chọn thành công
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(byElement, false));

        //Wait cho frame/Iframe xuất hiện sau đó switch vào
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(byElement));

        //Wait cho 1 đoạn lệnh JS thực thi không trả về bất kỳ Exeption nào
        explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions("return arguments[0].validationMessage;"));

        //Wait phủ định lại điều kiện wait
        explicitWait.until(ExpectedConditions.not(ExpectedConditions.javaScriptThrowsNoExceptions("return arguments[0].validationMessage;")));

        //Wait cho list element = bao nhiêu item
        List<WebElement> allNumberSelected = explicitWait.until(ExpectedConditions.numberOfElementsToBe(byElement, 10));
        Assert.assertEquals(allNumberSelected.size(), 10);

        // ít hơn 10 phần tử
        explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(byElement, 10));

        // nhiều hơn 10 phần tử
        explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(byElement, 10));

        //Wait cho số lượng window/tab bằng bao nhiêu
        explicitWait.until(ExpectedConditions.numberOfWindowsToBe(3));

        //Wait cho 1 đoạn text bằng tuyệt đối
        explicitWait.until(ExpectedConditions.textToBe(byElement,""));
        explicitWait.until(ExpectedConditions.textMatches(byElement, Pattern.compile("&%&..")));

        //Wait cho 1 element hay bị change/Refresh lại/Update lại
        explicitWait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(byElement)));
    }

    @Test
    public void TC_02_() {


    }

    // 3- Clean: Delete data test/account/close browser/...
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}