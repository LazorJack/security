package com.lazorjack.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(SecurityApplication.class)
public class SecurityApplicationTests {

	@Test
	public void contextLoads() {
	}

}
