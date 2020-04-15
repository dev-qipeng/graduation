package site.qipeng;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestEncoder {

    @Test
    public void encoder() {
        String password = "admin";
        String encoderPassword = "$2a$10$WqeOGbuNQzSxVz4/rjLFk.QnBES5U5sBNKn5qx4CV/vPJbhgw1p4e";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String enPassword = encoder.encode(password);
        System.out.println(enPassword);

        if (encoder.matches(password, encoderPassword)){
            System.out.println("true");
        }else {
            System.out.println("false");
        }
    }
}
