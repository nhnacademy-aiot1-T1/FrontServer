package com.nhnacademy.front.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void successTest(){
		int actual = 2;
		Assertions.assertEquals(2,actual);
	}

}
