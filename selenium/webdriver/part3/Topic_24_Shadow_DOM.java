package webdriver.part3;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.security.Key;
import java.time.Duration;
import java.util.List;

public class Topic_24_Shadow_DOM {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_Shadow_DOM_Github_IO() throws InterruptedException {
        driver.get("https://automationfc.github.io/shadow-dom/");

        // Không support xpath, chỉ support với cssSelector

        //Element cha chua shadow_host
        WebElement shadowHostParent = driver.findElement(By.cssSelector("div#shadow_host"));
        //Lấy element chưa shadow root
        SearchContext firstShadow = shadowHostParent.getShadowRoot();

        //Thao tac trong shadow 1
        System.out.println(firstShadow.findElement(By.cssSelector("span.info")).getText());
        Assert.assertEquals(firstShadow.findElement(By.cssSelector("span.info")).getText(), "some text");
        Thread.sleep(2000);

        //Element chứa nested_shadow_host
        WebElement secondHostShadow = firstShadow.findElement(By.cssSelector("div#nested_shadow_host"));
        //Lấy element chưa shadow root
        SearchContext secondShadow = secondHostShadow.getShadowRoot();

        //Thao tác trong shadow 2
        System.out.println(secondShadow.findElement(By.cssSelector("div#nested_shadow_content>div")).getText());
        Assert.assertEquals(secondShadow.findElement(By.cssSelector("div#nested_shadow_content>div")).getText(), "nested text");
        Thread.sleep(2000);
    }

    @Test
    public void TC_02_Shadow_DOM_Book() throws InterruptedException {
        driver.get("https://books-pwakit.appspot.com/");

        WebElement booksElement = driver.findElement(By.cssSelector("book-app[apptitle='BOOKS']"));
        SearchContext booksShadowRoot = booksElement.getShadowRoot();

        WebElement appToolbarElement = booksShadowRoot.findElement(By.cssSelector("app-header>app-toolbar.toolbar-bottom>book-input-decorator>input"));
        appToolbarElement.sendKeys("Harry Potter");
        appToolbarElement.sendKeys(Keys.ENTER);
        Thread.sleep(5000);

        //Lấy ra title của các sách được hiển thị
        WebElement bookExploreElement = booksShadowRoot.findElement(By.cssSelector("main>book-explore"));
        SearchContext bookExploreShadowRoot = bookExploreElement.getShadowRoot();

        //Lấy ra list title
        List<WebElement> listElementLi = bookExploreShadowRoot.findElements(By.cssSelector("section>ul>li"));
        for (WebElement element : listElementLi) {
            WebElement bookItemElement = element.findElement(By.cssSelector("book-item"));
            SearchContext bookItemShadowRoot = bookItemElement.getShadowRoot();
            System.out.println(bookItemShadowRoot.findElement(By.cssSelector("div.info-section h2")).getText());
        }
    }

    // 3- Clean: Delete data test/account/close browser/...
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}