package es.deusto.ingenieria.sd.auctions.server.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Service
public class GoogleGateway implements IGateway {

	private static GoogleGateway instance;

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	private RestTemplate restTemplate;

	// Host and port NOT hard-coded: Defined in application.properties
	// @Value("${spring.server.url}")
	private String serverURL = "http://localhost";

	// @Value("${server.port}")
	private int serverPort = 8888;

	public GoogleGateway() {
	}
	
	public void setInstance(GoogleGateway googleGateway) {
		instance = googleGateway;
	}
	
	public static GoogleGateway getInstance() {
		if (instance == null) {
			instance = new GoogleGateway();
			SpringApplication.run(GoogleGateway.class);
		}
		return instance;
	}

	public boolean userValidation(String email) {
		boolean verified = false;
		System.out.println("googleGateway: " + serverURL + ":" + String.valueOf(serverPort) + "/user/email/{email}");
		try {
			verified = restTemplate.getForObject(serverURL + ":" + String.valueOf(serverPort) + "/user/email/{email}",
					boolean.class, email);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return verified;
	}

	public boolean passwordValidation(String email, String password) {
		boolean verified = restTemplate.getForObject(
				serverURL + ":" + String.valueOf(serverPort) + "/user/verify/{email}/{password}", boolean.class, email,
				password);
		return verified;
	}
}