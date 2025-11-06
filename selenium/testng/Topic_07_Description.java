package testng;

import org.testng.annotations.Test;

public class Topic_07_Description {

// Tên tc = hàm/ fuction/ method của Java
    // Theo convention của ngôn ngữ

    // Chú thích/ diễn giai note
    @Test(description = "Đăng nhập với email không hợp lệ") // Sẽ chỉ chạy các tc có description
    public void shoudldBeRegisterFailWithInvalidEmail(){
        System.out.println("shouldBeRegisterFailWithInvalidEmail");

    }

    @Test(description = "")
    public void shouldBeLoginPass(){
        System.out.println("shouldBeLoginPass");

    }

    @Test(enabled = false)//Skip không chạy testcase này
    public void shouldBeLoginFail(){
        System.out.println("shouldBeLoginFail");

    }
}
