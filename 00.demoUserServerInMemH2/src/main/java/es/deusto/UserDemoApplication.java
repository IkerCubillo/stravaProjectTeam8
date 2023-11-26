package es.deusto;


import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Value;


@SpringBootApplication
public class UserDemoApplication {
	
	private static final Logger log= LoggerFactory.getLogger(UserDemoApplication.class);
	 
	@Value("${spring.mail.host}")		
	private String host;
	@Value("${spring.mail.port}")		
	private int port;
	@Value("${spring.mail.username}")
    private String sender;
	@Value("${spring.mail.password}")
	private String password;
	 

    public static void main(String[] args) {
        SpringApplication.run(UserDemoApplication.class, args);
        
          }
}
    
