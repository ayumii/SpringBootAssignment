package com.cdit.webapp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CditSpringBootApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	private String createURLWithPort(String uri){
        return "http://localhost:" + port + uri;
    }
	
	@Test
	public void testGetUsersSuccess() {		
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = testRestTemplate.exchange(
				createURLWithPort("/users"),
				HttpMethod.GET, entity, String.class);

		String expectedJsonResult = "{\"results\":[{\"firstName\":\"Lalu\",\"lastName\":\"Lee\",\"salary\":2000},{\"firstName\":\"Tey\",\"lastName\":\"Chan\",\"salary\":4000},{\"firstName\":\"Raymond\",\"lastName\":\"Chua\",\"salary\":3999.09},{\"firstName\":\"Desmond\",\"lastName\":\"Chia\",\"salary\":0.01},{\"firstName\":\"Dennis\",\"lastName\":\"Koh\",\"salary\":0}]}";		
		JSONAssert.assertEquals(expectedJsonResult, response.getBody(), false);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
