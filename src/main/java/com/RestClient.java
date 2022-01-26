package com;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.model.Book;
import com.model.User;

@Component
public class RestClient {
	
	static RestTemplate restTemplate = new RestTemplate();
	
	private static final String LOGIN_API = "http://localhost:9090/login";
	private static final String GET_ALL_BOOK_API = "http://localhost:9090/getallbooks";
	private static final String GET_BOOK_BY_ID_API = "http://localhost:9090/getbook/{id}";
	private static final String ADD_BOOK_API = "http://localhost:9090/addbook";
	private static final String UPDATE_BOOK_API = "http://localhost:9090/updatebook";
	private static final String DELETE_BOOK_API = "http://localhost:9090/deletebook/{id}";
	private static final String GET_ALL_AUTHORS = "http://localhost:9090/getallauthors";
	
	
	public static void main(String[] args) throws JsonProcessingException {
	
	}
	public boolean login(User user) {

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<String> entity = new HttpEntity<String>(new Gson().toJson(user), headers);
			
			restTemplate.postForLocation(LOGIN_API, entity);
			return true;
		}catch (Exception e) {
			return false;
		}		
	}
	
	public List<Book> getAllBook() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		try {
			ResponseEntity<List<Book>> response = restTemplate.exchange(GET_ALL_BOOK_API,HttpMethod.GET,entity,new ParameterizedTypeReference<List<Book>>() {});
			return response.getBody();
		}catch(Exception e) {
			return null;
		}
	}
	
	public Book getBookById(int id) {
		Map<String, Integer> param = new HashMap<>();
		param.put("id", id);
		try {
			ResponseEntity<Book> response = restTemplate.getForEntity(GET_BOOK_BY_ID_API, Book.class, param);
			return response.getBody();
		}catch(Exception e) {
			return null;
		}
	}
	
	public void addBook(Book book) throws JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(new Gson().toJson(book), headers);
		restTemplate.postForLocation(ADD_BOOK_API, entity);
		
	}
	
	public void updateBook(Book book) throws JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(new Gson().toJson(book), headers);
		restTemplate.put(UPDATE_BOOK_API, entity);
	}
	
	public void deleteBook(int id) {
		Map<String, Integer> param = new HashMap<>();
		param.put("id", id);
		try {
			restTemplate.delete(DELETE_BOOK_API, param);
		}catch(Exception e) {
		}
	}
	
	public List<String> getAllAuthors() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		try {
			ResponseEntity<List<String>> response = restTemplate.exchange(GET_ALL_AUTHORS,HttpMethod.GET,entity,new ParameterizedTypeReference<List<String>>() {});
			return response.getBody();
		}catch(Exception e) {
			return null;
		}
	}

}


