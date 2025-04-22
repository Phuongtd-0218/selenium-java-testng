package javaSDET;

import com.beust.ah.A;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Topic01_DataType {
    // 2 nhóm kiểu dữ liệu

    //Cách khai bao:
    // access modifier: phạm vi truy cập(private/public/protected/ default)
    // 1- Access Modifier - Kiểu dữ liệu - Tên biến - Giá trị của biến (Ngoài hàm/ Trong hàm đều được)
    public char cName = 'b';
    // 2-1 Access Modifier - Kiểu dữ liệu - Tên biến
    private char cAddress;
    // 2-2 Tên biến - Giá trị gán sau(Hàm)
    public void clickToElement(){
        cAddress = 'c';
    }



    // Nhóm 1: Kiểu dữ liệu nguyên thủy (8 kiểu) - primitive type
    //  char: kí tự (character)

    //  byte/short/int/long: số nguyên
    // khi gán giá trị thì không ằm trong dấu gì
    byte bNumber = -1;

    short sNumber = 12000;

    int iNumber = 34500000;

    long lNumer = 2342342;
    //  float/double: số thực

    float lNumber = 15.5f;

    double dNumber = 18.98d;
    //  boolean: logic

    boolean Gender = true;

    // Nhóm 2: kiểu dữ liệu tham chiều reference type/non-primitive
    // String: chuỗi
    //Khi gán giá trị (Khởi tạo) thì nằm trong dấu nháy đôi ("")

    String fullName = "Automation FC";

    //class
    FirefoxDriver fDriver = new FirefoxDriver();

    Actions actions = new Actions(fDriver);
    //Interface
    WebDriver diver;

    JavascriptExecutor jsExecutor;
    //Array
    String[] studentName = {"Lân","Hiền", "Lương"};
    Integer[] studentPhone = {8888454, 77776723, 999999999};
    //List/Set
    List<String> studentAddress = new ArrayList<>();
    //Map
    Map<String,Integer> zip = new HashMap<String, Integer>();

    //Object

    Object name = 12345;
    Object isDisplayed = true;

    //Convention: Quy ước khi lập trình
    //Tên biến/ tên hàm: viết dưới dạng camel case
    //Chữ cái đầu tiên luôn viết thường
    // Ví dụ: biến: name/address/city/phone/zipCode
    // hàm: clickToElement/getUserName/ getPhoneNumber/ selectItemInDropdown
}
