package webdriver.part3;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;

import org.openqa.selenium.devtools.v136.network.Network;
import org.openqa.selenium.devtools.v136.network.model.Headers;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Topic_17_Authentication_Alert {

    //JUnit - dùng cho Unit Test
    //TestNG - dùng cho UI test

    // Thành phần/Cấu trúc trong 1 test case

    // 1- Setup: Os/Browser/Web/Page/Data/Variable/Object/...
    WebDriver driver;

    String userName = "admin";
    String password = "admin";

    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    // 2- Action/Execute: Tương tác lên element nào/nhập liệu/verify..
    // Cần có chỉ dẫn - annotations để testcase hoạt động
    @Test
    public void TC_01_Authenticator_Url() {
        // http/ https:// + userName +:+ password +  @ Url

        driver.get("http://" + userName + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");

        Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(),
                "Congratulations! You must have the proper credentials.");
    }

    @Test
    public void TC_02_Authenticator_Navigate() {

        driver.get("http://the-internet.herokuapp.com/");;
        String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");

        driver.get(getUrl(basicAuthenLink,userName,password));

        Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(),
                "Congratulations! You must have the proper credentials.");

    }

    public String getUrl (String link, String userName, String password){
        String[] linkArrays = link.split("//");

       return linkArrays[0] + userName + ":" + password + "@" + linkArrays[1];
    }
     @Test
     public void TC_03_Authenticator_PDA(){
         // Get DevTool object
         DevTools devTools = ((HasDevTools) driver).getDevTools();

         // Start new session
         devTools.createSession();

         // Enable the Network domain of devtools
         devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

         // Encode username/ password
         Map<String, Object> headers = new HashMap<String, Object>();
         String basicAuthen = "Basic " + new String(new Base64().encode(String.format("%s:%s", "admin", "admin").getBytes()));
         headers.put("Authorization", basicAuthen);

         // Set to Header
         devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

         driver.get("https://the-internet.herokuapp.com/basic_auth");

         Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(),
                 "Congratulations! You must have the proper credentials.");
     }

    // 3- Clean: Delete data test/account/close browser/...
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}