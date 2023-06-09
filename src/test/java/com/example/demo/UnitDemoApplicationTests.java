package com.example.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.service.DemoApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@WebMvcTest
class UnitDemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DemoApplicationService demoApplicationService;

	@Test
	@DisplayName("GET /のテスト")
	void helloWorld() throws Exception  {
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"))
		.andExpect(content().string(containsString("Hello World")))
		.andExpect(content().contentType("text/html;charset=UTF-8"))
		.andExpect(xpath("/html/body/p").string("Hello World"))
		.andExpect(xpath("//p").string("Hello World"));
	}

	@Test
	@DisplayName("GET /formのテスト")
	void getForm() throws Exception {
		mockMvc.perform(get("/form"))
		.andExpect(status().isOk())
		.andExpect(view().name("form"))
		.andExpect(content().contentType("text/html;charset=UTF-8"))
		.andExpect(xpath("//label[@for='name']").exists());
	}

	@Nested
	@DisplayName("POST /formのテスト")
	class PostFormTest {

		@Test
		@DisplayName("パターン1")
		void postForm1() throws Exception {
			mockMvc.perform(
				post("/form")
				.param("name", "taro")
				.param("email", "taro@gmail.com")
			)
			.andExpect(status().isOk())
			.andExpect(view().name("confirm"))
			.andExpect(content().contentType("text/html;charset=UTF-8"))
			.andExpect(xpath("/html/body/span").string(containsString("氏名： taro")))
			.andExpect(xpath("/html/body/span").string(containsString("E-Mail： taro@gmail.com")))
			.andExpect(xpath("/html/body/span").string(containsString("年齢： 100")));
		}
	}

	@Nested
	@DisplayName("GET /apiのテスト")
	class ApiTest {

		@Test
		@DisplayName("パターン1")
		void getApi1() throws Exception {
			// DemoApplicationService#getAnimalをモック
			when(demoApplicationService.getAnimal()).thenReturn("cat");

			mockMvc.perform(
				get("/api")
				.param("name", "taro")
				.param("email", "taro@gmail.com")
				.param("age", "10")
			)
			.andExpect(status().isOk())
			.andExpect(view().name("form"))
			.andExpect(content().contentType("text/html;charset=UTF-8"))
			.andExpect(xpath("//input[@type='text']/@value").string("taro"))
			.andExpect(xpath("//input[@type='email']/@value").string("taro@gmail.com"))
			.andExpect(xpath("//input[@type='number']/@value").string("10"))
			.andExpect(xpath("/html/body/p").string("cat"));
		}
	}

	@Nested
	@DisplayName("GET /xml-testのテスト")
	class XmlTest {

		@Test
		@DisplayName("Accept-Language=ja-JP")
		void getApi1() throws Exception {
			mockMvc.perform(
				get("/xml-test")
				.header(HttpHeaders.ACCEPT_LANGUAGE, "ja-JP")
			)
			.andExpect(status().isOk())
			.andExpect(view().name("xml-test"))
			.andExpect(content().contentType("text/html;charset=UTF-8"))
			.andExpect(xpath("//Message/@imgsrc").string("http://hogehoge"))
			.andExpect(xpath("//Message/span[@id='message']").string("fuga"))
			.andExpect(xpath("//Contents[1]").string("Apple"))
			.andExpect(xpath("//Contents[2]").string("Orange"))
			.andExpect(xpath("//Contents[3]").string("Melon"))
			.andExpect(xpath("//IoScreen/p[2]").string("これはメッセージです end。"));
		}

		@Test
		@DisplayName("Accept-Language=en")
		void getApi2() throws Exception {
			mockMvc.perform(
				get("/xml-test")
				.header(HttpHeaders.ACCEPT_LANGUAGE, "en")
			)
			.andExpect(status().isOk())
			.andExpect(view().name("xml-test"))
			.andExpect(content().contentType("text/html;charset=UTF-8"))
			.andExpect(xpath("//IoScreen/p[2]").string("This is message end."));
		}
	}

}
