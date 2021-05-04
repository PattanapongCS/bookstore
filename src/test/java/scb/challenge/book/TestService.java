package scb.challenge.book;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import scb.challenge.book.model.JwtRequest;
import scb.challenge.book.model.UserPostRequest;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestService {
	
	@LocalServerPort
	private int randomServerPort;
	
	private String url="http://localhost:";
	
	private RestTemplate restTemplate = new RestTemplate();
	
	
	
	@Test
	@DisplayName("unit test of the BookService")
	public void getBookSuccess() throws URISyntaxException {
		final String baseUrl = url + randomServerPort + "/book";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		// Verify success
		assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	@DisplayName("unit test of the login service")
	public void getLogin() throws URISyntaxException {
		final String baseUrl = url + randomServerPort + "/login";
		URI uri = new URI(baseUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JwtRequest req = new JwtRequest();
		req.setUsername("admin");
		req.setPassword("admin");
		HttpEntity<JwtRequest> requestEntity = new HttpEntity<>(req, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(uri,requestEntity, String.class);

		// Verify success
		assertEquals(200, result.getStatusCodeValue());
	}
	
//	@Test
	@DisplayName("unit test of the create user service")
	public void createUserSuccess() throws URISyntaxException {
		final String baseUrl = url + randomServerPort + "/user";
		URI uri = new URI(baseUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		UserPostRequest req = new UserPostRequest();
		req.setName("test name");
		req.setSurname("test surname");
		req.setDate_of_birth(new Date());
		req.setUsername("test");
		req.setPassword("test");
		HttpEntity<UserPostRequest> requestEntity = new HttpEntity<>(req, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(uri,requestEntity, String.class);

		// Verify success
		assertEquals(200, result.getStatusCodeValue());
	}
	
}
