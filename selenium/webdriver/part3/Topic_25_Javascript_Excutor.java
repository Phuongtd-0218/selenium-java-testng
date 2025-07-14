package webdriver.part3;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_25_Javascript_Excutor {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;

    //Khai báo JSExecutor
    JavascriptExecutor jsExecutor;

    WebDriverWait explicitWait;
    Random random;
    String email;
    Alert alert;

    @BeforeClass
    public void initialBrowser() {
        driver = new EdgeDriver();

        // Ép kiểu driver sang JSE - ép kiểu tường minh
        jsExecutor = (JavascriptExecutor) driver;
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        email = "automatio" + new Random().nextInt(9999) + "@gmail.com";
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_Techpanda_C1() throws InterruptedException {


        jsExecutor.executeScript("window.location = 'https://live.techpanda.org/'");
//        explicitWait.until(ExpectedConditions.urlToBe("http://live.techpanda.org/"));
        Thread.sleep(5000);

        String getDomain = (String) jsExecutor.executeScript(" return document.domain");
        Assert.assertEquals(getDomain, "live.techpanda.org");

        String getURL = (String) jsExecutor.executeScript("return document.URL");
        Assert.assertEquals(getURL, "https://live.techpanda.org/");

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Mobile']")));
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Mobile']")));
        Thread.sleep(5000);

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Samsung Galaxy']" +
                "/parent::h2/following-sibling::div[@class='actions']/button")));
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Samsung Galaxy']" +
                "/parent::h2/following-sibling::div[@class='actions']/button")));
        Thread.sleep(5000);

        String textInPage = (String) jsExecutor.executeScript("return document.documentElement.innerText");
        Assert.assertTrue(textInPage.contains("Samsung Galaxy was added to your shopping cart."));

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Customer Service']")));
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Customer Service']")));
        Thread.sleep(5000);

        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("input#newsletter")));

        jsExecutor.executeScript("arguments[0].setAttribute('value','" + email + "')", driver.findElement(By.cssSelector("input#newsletter")));
        Thread.sleep(3000);

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[title='Subscribe']")));
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button[title='Subscribe']")));
        Thread.sleep(5000);

        explicitWait.until(ExpectedConditions.alertIsPresent());
        Thread.sleep(3000);
        alert.accept();

        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("ul.messages>li.success-msg"))));
        Assert.assertTrue(textInPage.contains("Thank you for your subscription."));

        jsExecutor.executeScript("window.location = 'https://www.facebook.com/'");
    }

    @Test
    public void TC_02_TechPanda_C2() {
        //Dùng hàm custom

        navigateToUrlByJS("");

        // Get Domain và so sánh

        // Get PageURL và so sánh

        // Click to Mobile Tab

        // Click ADD TO CART : SAMSUNG GALAXY

        // Verify success-mess

        // Open Customer Service Page

        // Verify Page Title

        // Scroll to Element NewSletter

        // Send key in to Input Tag

        // Click to Subscribe Button

        // Verify Success - mess

        // navigate to domain facebook.com
    }

    @Test
    public void TC_03_Rode() throws InterruptedException {
        jsExecutor.executeScript("window.location = 'https://account.rode.com/login'");

        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        WebElement inputEmail =  driver.findElement(By.xpath("//input[@id='email']"));

        //Empty
        loginButton.click();

        String emptyEmailMess = getElementValidationMessage("//input[@id='email']");
        Assert.assertEquals(emptyEmailMess, "このフィールドに入力してください。");
        Thread.sleep(5000);

        //Invalid Email]
        String invaliEmailData = "aaa";
        inputEmail.sendKeys(invaliEmailData);
        Thread.sleep(2000);

        String invalidEmailMess = getElementValidationMessage("//input[@id='email']");
        if (driver.toString().contains("EdgeDriver")) {
            //Edge
            Assert.assertEquals(invalidEmailMess, "メール アドレスに '@' を含めてください。'" + invaliEmailData + "' の部分に '@' がありません。");
            Thread.sleep(5000);
        } else if (driver.toString().contains("ChromeDriver")) {
            //Chorme
            // メール アドレスに「@」を挿入してください。「aa」内に「@」がありません。
            Assert.assertEquals(invalidEmailMess, "メール アドレスに「@」を挿入してください。「" + invaliEmailData + "」内に「@」がありません。");
            Thread.sleep(5000);
        } else {
            System.out.println("Get check mess by FireFox - 1");
        }
        inputEmail.clear();
        Thread.sleep(2000);


        invaliEmailData = "aaa@";
        inputEmail.sendKeys(invaliEmailData);
        Thread.sleep(2000);

        invalidEmailMess = getElementValidationMessage("//input[@id='email']");

        if (driver.toString().contains("EdgeDriver")) {
            //Edge
            //"'@' の後に、アドレスの一部を入力してください。''aaa@' は不完全です。"
            Assert.assertEquals(invalidEmailMess, "'@' の後に、アドレスの一部を入力してください。''"+invaliEmailData+"' は不完全です。");
            Thread.sleep(5000);
        } else if (driver.toString().contains("ChromeDriver")) {
            //Chrome
            //'「aa@」は完全なメールアドレスではありません。「@」に続く文字列を入力してください。'
            Assert.assertEquals(invalidEmailMess, "「" + invaliEmailData + "」は完全なメールアドレスではありません。「@」に続く文字列を入力してください。");
            Thread.sleep(5000);
        } else {
            System.out.println("Get check mess by FireFox - 2");
        }

        inputEmail.clear();
        Thread.sleep(2000);

        invaliEmailData = "aaa@aaa.";
        inputEmail.sendKeys(invaliEmailData);
        Thread.sleep(2000);

        invalidEmailMess = getElementValidationMessage("//input[@id='email']");

        if (driver.toString().contains("EdgeDriver")) {
            //Edge
            //"'aaa.' では、'.' が不適切な位置で使用されています。"
            Assert.assertEquals(invalidEmailMess, "'" + invaliEmailData.split("@")[1] +"' では、'.' が不適切な位置で使用されています。");
            Thread.sleep(5000);
        } else if (driver.toString().contains("ChromeDriver")) {
            //Chrome
            //'「aaa.」内の「.」の位置が間違っています。'
            Assert.assertEquals(invalidEmailMess, "「" + invaliEmailData.split("@")[1] + "」内の「.」の位置が間違っています。");
            Thread.sleep(5000);
        } else {
            System.out.println("Get check mess by FireFox - 2");
        }

        inputEmail.clear();
        Thread.sleep(2000);

        invaliEmailData = "aaa@aaa.aaa";
        inputEmail.sendKeys(invaliEmailData);
        Thread.sleep(2000);
        inputEmail.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

//        loginButton.click();
        String emptyPasswordMess = getElementValidationMessage("//input[@id='password']");

        Assert.assertEquals(emptyPasswordMess, "このフィールドに入力してください。");
        Thread.sleep(5000);

        driver.findElement(By.cssSelector("input#password")).sendKeys("1");
        loginButton.click();

        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='These credentials do not match our records.']")).isDisplayed());
    }

    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void sleepInSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].textContent;", getElement(locator));
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }

    // 3- Clean: Delete data test/account/close browser/...
//    @AfterClass
//    public void cleanBrowser() {
//        driver.quit();
//    }


}