package com.pagantis.admintool.controller;

import java.net.URI;
import java.net.URISyntaxException;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.pagantis.admintool.AdmintoolApplication;
import com.pagantis.admintool.service.WalletService;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
@ContextConfiguration(classes = {WalletService.class})
public class WalletControllerTest{
	



	@MockBean
	private WalletService walletServisce;
	 
	 
	 @Test
	 public void testGetAllWalletsOfUserSuccess() throws URISyntaxException 
	 {
	     RestTemplate restTemplate = new RestTemplate();	      
	     final String baseUrl = "http://localhost:" + 8080 + "/api/wallets/user/1";
	     URI uri = new URI(baseUrl);	  
	     ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);	      
	     //Verify request succeed
	     Assert.assertEquals(200, result.getStatusCodeValue());
	     
	 }
	 
	 public void testGetAllWalletsOfUserError() throws URISyntaxException 
	 {
	     RestTemplate restTemplate = new RestTemplate();	      
	     final String baseUrl = "http://localhost:" + 8080 + "/api/wallets/user/bla-bla";
	     URI uri = new URI(baseUrl);	  
	     ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);	      
	     //Verify request succeed
	     Assert.assertEquals(400, result.getStatusCodeValue());
	     
	 }
	 	
	 
	 @Test
	 public void testGetWalletByIdSuccess() throws URISyntaxException 
	 {
	     RestTemplate restTemplate = new RestTemplate();	      
	     final String baseUrl = "http://localhost:" + 8080 + "/api/wallet/2";
	     URI uri = new URI(baseUrl);	  
	     ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);	      
	     //Verify request succeed
	     Assert.assertEquals(200, result.getStatusCodeValue());
	     
	 }


}
