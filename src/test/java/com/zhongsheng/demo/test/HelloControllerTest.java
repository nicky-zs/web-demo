package com.zhongsheng.demo.test;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhongsheng.demo.WebDemoServer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(WebDemoServer.class)
@WebIntegrationTest
public class HelloControllerTest {

	private static final ObjectMapper om = new ObjectMapper();

	@Resource
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testSayHello() throws Exception {
		MediaType mt = MediaType.parseMediaType("application/json;charset=UTF-8");

		ResultActions r = mockMvc.perform(get("/").accept(mt));
		r.andExpect(status().isOk());

		String s = r.andReturn().getResponse().getContentAsString();

		@SuppressWarnings("unchecked")
		Map<String, Object> resultMap = om.readValue(s, Map.class);
		assertEquals(resultMap.get("msg"), "hello");
	}
}
