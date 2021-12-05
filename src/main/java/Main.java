import com.sample.Order;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args[0] == null|| args[0].trim().isEmpty()) {
            System.out.println("Didn't recieve file !");
        }
        else
        {
            System.out.println("Got file path "+args[0]);
            Order obj=new Order();
            obj.processOrder(args[0]);
        }
    }
}
