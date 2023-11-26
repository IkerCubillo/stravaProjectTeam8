package es.deusto.ingenieria.sd.auctions.server.services;


import es.deusto.ingenieria.sd.auctions.server.gateway.FacebookGateway;
import es.deusto.ingenieria.sd.auctions.server.gateway.GoogleGateway;

//TODO: Implement Singleton Pattern
public class LoginAppService {
		
	public boolean login(String email, String password, String account) {
		//TODO: Get User using DAO and check 
		//Password always correct
		if(account.equals("Google")) {
			return googlePasswordValidation(email, password);
		} else if(account.equals("Facebook")) {
			return facebookPasswordValidation(email, password);
		} else {
			return false;
		}
	}
	
	public boolean register(String email, String account) {
		//Check user email exists in service (Google and Facebook always exists
		
		if(account.equals("Google")) {
			return googleUserValidation(email);
		} else if(account.equals("Facebook")) {
			return facebookUserValidation(email);
		} else {
			return false;
		}
	}

	public boolean facebookUserValidation(String email) {
		//Get conversion rate from Service Gateway
		return FacebookGateway.getInstance().facebookUserValidation(email);
	}

	public boolean facebookPasswordValidation(String email, String password) {
		//Get conversion rate from Service Gateway
		return FacebookGateway.getInstance().facebookPasswordValidation(email, password);
	}
	
	public boolean googleUserValidation(String email) {
		//Get conversion rate from Service Gateway
		return GoogleGateway.getInstance().googleUserValidation(email);
	}

	public boolean googlePasswordValidation(String email, String password) {
		//Get conversion rate from Service Gateway
		return GoogleGateway.getInstance().googlePasswordValidation(email, password);
	}
	
	public LoginAppService getInstance() {

		LoginAppService appSer = new LoginAppService();

		return appSer;
	}
}