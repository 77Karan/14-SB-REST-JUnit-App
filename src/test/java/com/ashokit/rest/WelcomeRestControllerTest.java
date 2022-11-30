package com.ashokit.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ashokit.service.WelcomeService;

@WebMvcTest(value = WelcomeRestController.class) //Used to specify which class that we performing Unit Testing
public class WelcomeRestControllerTest {

	@Autowired
	private MockMvc mockMvc;  //To perform  mocking because our RestController is dependent on Service Component and to send a request to our Rest Controller

	@MockBean //This is used to specify for which Class we need to create Mock object
	private WelcomeService welcomeService; 

	@Test
	public void test_welcomeMsg() throws Exception {

		when(welcomeService.getWelcomeMsg()).thenReturn("this is test msg"); //Defining Behavior.

		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/welcome"); //For Normal Java class we create Object but For RestController we invoke Rest methods using Url
		                                                                                   //Creating a Get Request for our Rest Controller
		
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn(); //What ever the request we are created we are performing(sending) that request and to retrun MvcResult

		// String responseStr = mvcResult.getResponse().getContentAsString();

		int status = mvcResult.getResponse().getStatus(); //To get response and to get status code of that response

		assertEquals(200, status);

	}
}
