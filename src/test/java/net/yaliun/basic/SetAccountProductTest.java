package net.yaliun.basic;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class SetAccountProductTest{
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	StringBuilder setAccoutXml;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		//this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).alwaysDo(print()).alwaysExpect(status().isOk()).build();

		setAccoutXml = new StringBuilder();
		
		setAccoutXml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
					.append("<SetAccountProductRequest>")
					.append("<request-id>TEST-request12345</request-id>")
					.append("<msisdn>821043400369</msisdn>")
					.append("<product-code>CODE-A</product-code>")
					.append("</SetAccountProductRequest>");
	}
	
	@Test
	public void testSetAccountProduct() throws Exception {
		
		mockMvc.perform(					
					post("/api/setAccountProduct")
						.contentType(MediaType.APPLICATION_XML)
						.content(setAccoutXml.toString().getBytes()))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/xml"));
				//.andExpect(content().string("Hello world!"));
	}
}
