package es.deusto.ingenieria.sd.auctions.server.services;


import es.deusto.ingenieria.sd.auctions.server.data.domain.User;

//TODO: Implement Singleton Pattern
public class LoginAppService {
		
	public boolean login(String email, String password) {
		//TODO: Get User using DAO and check 
		//Password always correct
		boolean correctPasword = true;
		
		return correctPasword;
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