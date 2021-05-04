package scb.challenge.book;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import scb.challenge.book.model.JwtRequest;

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
	
	@Test
	@DisplayName("unit test of the get user service")
	public void getUser() throws URISyntaxException, JSONException {
		final String baseUrl = url + randomServerPort + "/login";
		URI uri = new URI(baseUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JwtRequest req = new JwtRequest();
		req.setUsername("admin");
		req.setPassword("admin");
		HttpEntity<JwtRequest> requestEntity = new HttpEntity<>(req, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(uri,requestEntity, String.class);
		if (StringUtils.isNotBlank(result.getBody())) {
			JSONObject obj = new JSONObject(result.getBody());
			String getBaseUrl = url + randomServerPort + "/user?id={id}";
			HttpHeaders getHeaders = new HttpHeaders();
			getHeaders.setContentType(MediaType.APPLICATION_JSON);
			getHeaders.set("Authorization", obj.getString("token"));
			Map<String, Object> getReq = new HashMap<>();
			getReq.put("id", 1);
			HttpEntity<?> httpEntity  = new HttpEntity<>(getHeaders); 
			ResponseEntity<String> getResult = restTemplate.exchange(getBaseUrl, HttpMethod.GET, httpEntity,String.class, getReq);
			
			assertEquals(200, getResult.getStatusCodeValue());
		}
	}
	
}
