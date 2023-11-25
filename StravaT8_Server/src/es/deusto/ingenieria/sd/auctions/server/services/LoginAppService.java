package es.deusto.ingenieria.sd.auctions.server.services;


import es.deusto.ingenieria.sd.auctions.server.data.domain.User;

//TODO: Implement Singleton Pattern
public class LoginAppService {
		
	public User login(String email, String password) {
		//TODO: Get User using DAO and check 		
		User user = new User();
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
	
	public User register(String email, String account) {
		//Check user email exists in service (Google and Facebook always exists
		User user = new User();
		
		if(account.equals("Google")) {
			return user;
		} else if(account.equals("Facebook")) {
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