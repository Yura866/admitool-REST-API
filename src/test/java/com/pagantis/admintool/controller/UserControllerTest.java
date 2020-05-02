package com.pagantis.admintool.controller;

import com.pagantis.admintool.dto.UserDTO;
import com.pagantis.admintool.model.User;
import com.pagantis.admintool.model.Wallet;
import com.pagantis.admintool.service.UserService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@WebMvcTest(UserController.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
@ContextConfiguration(classes = {UserService.class})
public class UserControllerTest {	

	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;
	
	 @Before
	    public void init() {
		 
		 Wallet wallet = new Wallet(1L,200.00,"Shoping","3CC3X2gu58d6sedfsdfsfTRYEGDFWu4KjFHGGHG", "1CC3X2gu58d6wXUWMffpuzN9JAfTUWu4KjDFD",new User());		 
		 User user = new User(1L, "Bovik", "Potap", "vok@marvel.com", "Pagantis", "Barcelona ,España", "Barcelona", "España", "02664", "Cooper", Arrays.asList(wallet, new Wallet(), new Wallet()));	
		
		 UserDTO userDto = new UserDTO();
		 BeanUtils.copyProperties(user, userDto);
				 	       
		 Mockito.when(userService.findById(1L)).thenReturn(userDto);
		 
	}
	 
	 
	 @Test
	 public void testGetUsersListSuccess() throws URISyntaxException 
	 {
	     RestTemplate restTemplate = new RestTemplate();	      
	     final String baseUrl = "http://localhost:" + 8080 + "/api/users";
	     URI uri = new URI(baseUrl);	  
	     ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);	      
	     //Verify request succeed
	     Assert.assertEquals(200, result.getStatusCodeValue());
	     
	 }
	 
	 
	 @Test
	 public void testUserMissingHeader() throws URISyntaxException 
	 {
	     RestTemplate restTemplate = new RestTemplate();
	     final String baseUrl = "http://localhost:"+8080+"/api/users";
	     URI uri = new URI(baseUrl);
	     User user = new User(1L, "Bovik", "Potap", "vok@marvel.com", "Pagantis", "Barcelona ,España", "Barcelona", "España", "02664", "Cooper", Arrays.asList( new Wallet(), new Wallet()));
	      
	     HttpHeaders headers = new HttpHeaders();	  
	     HttpEntity<User> request = new HttpEntity<>(user, headers);	      
	     try
	     {
	         restTemplate.postForEntity(uri, request, String.class);
	         Assert.fail();
	     }
	     catch(HttpClientErrorException ex) 
	     {
	         //Verify bad request and missing header
	         Assert.assertEquals(405, ex.getRawStatusCode());
	         Assert.assertEquals(false, ex.getResponseBodyAsString().contains("Header"));
	     }
	 }	 
	 	 
	   
	   @Test
	    public void getUserByIdNotFound_404() throws Exception {
	        mockMvc.perform(get("/user/100")).andExpect(status().isNotFound());
	    } 
	

}
