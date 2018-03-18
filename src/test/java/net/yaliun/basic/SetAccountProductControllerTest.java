package net.yaliun.basic;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import net.yaliun.basic.controller.SetAccountProductController;
import net.yaliun.basic.domain.SetAccountProductReqVO;
import net.yaliun.basic.service.SetAccountProductService;

public class SetAccountProductControllerTest {

	private MockMvc mockMvc;
	
    @Mock
	SetAccountProductService service;
    
    @InjectMocks
    private SetAccountProductController setAccountProductController;    
	
	StringBuilder setAccoutXml;

	@Before
	public void setup() {
		 MockitoAnnotations.initMocks(this);
		
		this.mockMvc = MockMvcBuilders.standaloneSetup(setAccountProductController).build();
		
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
				
		//mockito
		when(service.sum()).thenReturn(11);
		
		mockMvc.perform(					
					post("/api/setAccountProduct")
						.contentType(MediaType.APPLICATION_XML)
						.content(setAccoutXml.toString().getBytes()))
				//.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/xml"));
				
				//.andExpect(content().string("Hello world!"));
		
		verify(service).sum();
		//verifyNoMoreInteractions(service);
	}
}
