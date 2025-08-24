package payment;

import org.testng.annotations.Test;

public class Payment_01_CRUD {

    @Test(groups = {"payment","regression"}) //Có thể nằm trong 1 hoặc nhiều groups - Nếu nằm trong nhiều groups sẽ viết dạng mảng (groups = {"gr1", "gr2"})
    public void TC_01(){
        System.out.println("Payment TC_01");

    }

    @Test(groups = {"payment","regression"})
    public void TC_02(){
        System.out.println("Payment TC_02");

    }

    @Test(groups = {"payment","regression"})
    public void TC_03(){
        System.out.println("Payment TC_03");

    }
}
