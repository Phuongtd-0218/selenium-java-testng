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

public class Topic_12_Custom_Dropdown {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;
    WebDriverWait expliciWait;

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();
        expliciWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_Jquery() throws InterruptedException {
        
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        selectItemCustomDropdown("span#speed-button","ul#speed-menu>li>div","Slower");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),"Slower");

        selectItemCustomDropdown("span#files-button","ul#files-menu>li>div","ui.jQuery.js");
        selectItemCustomDropdown("span#number-button","ul#number-menu>li>div","18");
        selectItemCustomDropdown("span#salutation-button","ul#salutation-menu>li>div","Prof.");
        Thread.sleep(4000);

    }
    //Dự án thực tế: 1 hàm để thao tác lên dropdown chỉ dùng cho 1 site
    //Không dùng cho nhiều application khác nhau
    //Cơ chế dropdown giống nhau

    private void selectItemCustomDropdown(String parentCss, String childCss, String items) throws InterruptedException {
        //Hành vi thể thiện thao tác lên dropdown
        // 1- Chờ cho dropdown có thể thao tác lên được (clickable)
        expliciWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(parentCss))).click();

        // 2- Click vào element nào để nó xổ ra cái dropdown ra
//        driver.findElement(By.cssSelector(parentCss)).click();
        Thread.sleep(3000);

        // 3- Chờ cho tất cả item load ra thành công(presence)
        expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));
        // 4- Tìm item mong muốn - duyệt các kết quả hiển thị ra sau đó chọn giá trị mong muốn
        List<WebElement> allItems = driver.findElements(By.cssSelector(childCss));
        // 5- Click lên item đó

        for (WebElement item : allItems){
            System.out.println(item.getText());
            if (item.getText().equals(items)){
                item.click();
                break;
            }
        }
    }

    @Test
    public void TC_02_React() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        selectItemCustomDropdown("div.dropdown","div.item>span","Aland Islands");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Aland Islands");
    }

    @Test
    public void TC_03_VueJS() throws InterruptedException {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemCustomDropdown("li.dropdown-toggle","ul.dropdown-menu>li>a","First Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"First Option");

        selectItemCustomDropdown("li.dropdown-toggle","ul.dropdown-menu>li>a","Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Second Option");

        selectItemCustomDropdown("li.dropdown-toggle","ul.dropdown-menu>li>a","Third Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Third Option");
    }

    // 3- Clean: Delete data test/account/close browser/...
    @AfterClass
    public void cleanBrowser(){
//        driver.quit();
    }

}