package site.qipeng;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestEncoder {

    @Test
    public void encoder() {
        String password = "123";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String enPassword = encoder.encode(password);
        System.out.println(enPassword);
    }
}
