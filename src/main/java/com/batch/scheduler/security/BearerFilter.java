package com.batch.scheduler.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.batch.scheduler.model.User;

@Component
public class BearerFilter {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${loanManagementUrl}")
	private String url;	
	
	public String generateToken() {
		String nurl = null;
		String bearerToken;
		String userName="User1";
		String password ="User1";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		//User user = new User("User1","User1");
		String requestBody = "{\"userName\":\"" + userName +
								"\",\"password\":\"" + password + "\"}";
		nurl= url + "/login";
		HttpEntity<String> entity = new HttpEntity<>(requestBody,headers);	
		ResponseEntity<? >response = restTemplate.exchange(
				nurl,HttpMethod.POST, entity, String.class);
		System.out.println(response.getBody());
		System.out.println(response);
		bearerToken= response.toString();
		int startIndex = bearerToken.indexOf("\"jwt\":\"") + 7;
		int endIndex = bearerToken.indexOf("\"", startIndex);
		System.out.println(startIndex+"  "+endIndex);
		String bresponse = bearerToken.substring(startIndex, endIndex);
		System.out.println("bresponse"+bresponse);
		return bresponse;
	}
}
