package es.deusto.ingenieria.sd.auctions.server.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${spring.server.url}")
	private String serverURL;

	@Value("${server.port}")
	private int serverPort;
	
	@Autowired
	public void setInstance(GoogleGateway googleGateway) {
		instance = googleGateway;
	}
	
	public static GoogleGateway getInstance() {
		if (instance == null) {
			SpringApplication.run(GoogleGateway.class);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public boolean userValidation(String email) {
		boolean verified = false;
		System.out.println("- Verifying Google email from '" + serverURL + ":" + String.valueOf(serverPort) + "/user/email/{email}' -> '" + email + "'");
		try {
			verified = restTemplate.getForObject(serverURL + ":" + String.valueOf(serverPort) + "/user/email/{email}",
					boolean.class, email);
			System.out.println("Recieving verification from '" + serverURL + ":" + String.valueOf(serverPort) + "/user/email/{email}' -> '" + verified + "'");
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return verified;
	}

	public boolean passwordValidation(String email, String password) {
		System.out.println("- Verifying Google password from '" + serverURL + ":" + String.valueOf(serverPort) + "/user/verify/{email}/{password}' -> '" + email + "' : '" + password + "'");
		boolean verified = restTemplate.getForObject(
				serverURL + ":" + String.valueOf(serverPort) + "/user/verify/{email}/{password}", boolean.class, email,
				password);
		System.out.println("Recieving verification from '" + serverURL + ":" + String.valueOf(serverPort) + "/user/verify/{email}/{password}' -> '" + verified + "'");
		return verified;
	}
}