//package com.fantasy.football.controllerTest;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.fantasy.football.controller.GraphQLQueryController;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(GraphQLQueryController.class)
//public class GraphQLQueryControllerIntegrationTest {
//
//	@Autowired
//	private MockMvc mvc;
//
//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@Test
//	public void okStatusWithJsonObjectTest() throws Exception {
//		String graphqlStr = ""
//				+ "mutation GetAllLeagues {\r\n" + 
//				"    getAllLeagues {\r\n" + 
//				"        leagueName\r\n" + 
//				"        teams {\r\n" + 
//				"            teamName\r\n" + 
//				"        }\r\n" + 
//				"    }\r\n" + 
//				"}";
//		mvc.perform(post("/graphql").content(graphqlStr).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//	}
//
//}
