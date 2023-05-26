package com.example.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dataset.CsvDataSetLoader;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class) // DBUnitでCSVファイルを使えるよう指定
@SpringBootTest
@Transactional // @DatabaseSetupで投入するデータをテスト処理と同じトランザクション制御とする。（テスト後に投入データもロールバックできるように）
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class, // このテストクラスでDIを使えるように指定
	TransactionDbUnitTestExecutionListener.class // @DatabaseSetupや＠ExpectedDatabaseなどを使えるように指定
})
@ActiveProfiles("devv")
@AutoConfigureMockMvc
public class DemoApplicationIntegrationTests {

  @Autowired
	private MockMvc mockMvc;

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

  @Nested
	@DisplayName("GET /apiのテスト")
	class ApiTest {

		@Test
		@DisplayName("パターン1")
		void getApi1() throws Exception {
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
			.andExpect(xpath("/html/body/p").string("dog"));
		}
	}

	@Test
	@DisplayName("GET user")
	@DatabaseSetup("classpath:dbunit/")
	void getUser() throws Exception {
		mockMvc.perform(
			get("/user")
			.param("userId", "2")
		)
		.andExpect(status().isOk())
		.andExpect(view().name("user"))
		.andExpect(content().contentType("text/html;charset=UTF-8"))
		.andExpect(xpath("/html/body/span").string("jiro"));
	}
}
