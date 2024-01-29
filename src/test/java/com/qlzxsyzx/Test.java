package com.qlzxsyzx;

import com.qlzxsyzx.AuthApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
public class Test {

    @org.junit.Test
    public  void test() {
        System.out.printf(String.valueOf(System.currentTimeMillis()));
    }
}
