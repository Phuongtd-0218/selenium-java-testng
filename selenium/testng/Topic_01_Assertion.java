package testng;

import org.testng.Assert;

public class Topic_01_Assertion {
    public static void main(String[] args) {
        // 3 hàm chính để kiểm tra tính đúng đắn của dữ liệu
        boolean gender = 3<5;

        // Kiểm tra dữ liệu phải ĐÚNG
        Assert.assertTrue(gender);

        // Kiểm tra dữ liệu phải SAI
        Assert.assertFalse(3>5);

        // Kiểm tra dữ liệu nó bằng với mong đợi (ACTUAL - EXPECTED)
        // Kiểm tra kiểu dữ liệu và giá trị phải đồng nhất
        Assert.assertEquals(5,"6"); //Fail
    }
}
