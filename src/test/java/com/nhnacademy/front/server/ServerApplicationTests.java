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
	void failTest(){
		int actual = 1;
		Assertions.assertEquals(2,actual);
	}

}
