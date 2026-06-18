package com.yashdev.springai.first;

import com.yashdev.springai.first.service.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FirstApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private ChatService chatService;
	@Test
	void testTemplateRender(){

//		System.out.println("template Render");
//
//		var output = this.chatService.chatTemplate();
//		System.out.println(output);
	}


}
