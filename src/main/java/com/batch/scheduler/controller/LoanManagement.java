package com.batch.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.batch.scheduler.security.BearerFilter;

@Service
public class LoanManagement {
	
	@Autowired
	private BearerFilter filter;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${loanManagementUrl}")
	private String url;
//	@Scheduled(fixedRate = 5000)
//	public void testOne() {
//		
//		System.out.println("Hi in test1");
//		System.out.println("running 30 sec scheduler");
//	}
	@Scheduled(cron = "0/15 * * * * *" )
public void testTwo() {
		
		System.out.println("Hi in test2");
		System.out.println("running 1min sec scheduler");
	
	String path = url+"/hi";
	HttpHeaders headers = new HttpHeaders();
	String token = "Bearer "+filter.generateToken();
	System.out.println("Token "+token);
	System.out.println("path  "+ path);
	headers.set("Authorization", token );
	HttpEntity<String> entity = new HttpEntity<>(headers);
	
	ResponseEntity<String> response = restTemplate.exchange(path,HttpMethod.GET,
												entity,String.class);
	
		System.out.println(response.getBody());
	}
}
