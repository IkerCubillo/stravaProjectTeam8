package es.deusto.ingenieria.sd.auctions.server.services;


import es.deusto.ingenieria.sd.auctions.server.data.domain.User1;

//TODO: Implement Singleton Pattern
public class LoginAppService {
		
	public User1 login(String email, String password) {
		//TODO: Get User using DAO and check 		
		User1 user = new User1();		
		user.setEmail("thomas.e2001@gmail.com");		
		//Generate the hash of the password
		String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex("$!9PhNz,");		
		user.setPassword(sha1);
		
		if (user.getEmail().equals(email) && user.getPassword().equals(password)) {		
			return user;
		} else {
			return null;
		}
	}
	
	public LoginAppService getInstance() {

		LoginAppService appSer = new LoginAppService();

		return appSer;
	}
}