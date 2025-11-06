package testng;

import org.testng.annotations.Test;

public class Topic_06_skip {

    //CRUD
    //Flow

    //Nếu không dùng priority thì cần tạo tên method theo thứ tự ABC.. để dễ quản lý, sửa chữa sau này
    @Test(priority = 1)
    public void shoudldBeRegisterFailWithInvalidEmail(){
        System.out.println("shouldBeRegisterFailWithInvalidEmail");

    }

    @Test(priority = 0)
    public void shouldBeLoginPass(){
        System.out.println("shouldBeLoginPass");

    }

    @Test(enabled = false)//Skip không chạy testcase này
    public void shouldBeLoginFail(){
        System.out.println("shouldBeLoginFail");

    }
}
