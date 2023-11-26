package es.deusto.ingenieria.sd.auctions.server.gateway;

import java.rmi.Naming;

import es.deusto.ingenieria.sd.auctions.currency.remote.IFacebookAuthorization;

public class FacebookGateway {

	private static FacebookGateway instance;
	private IFacebookAuthorization facebookAuthorization;
	
	private FacebookGateway() {
		try {		
			String URL = "//127.0.0.1:1099/Facebook";
			this.facebookAuthorization = (IFacebookAuthorization) Naming.lookup(URL);
		} catch (Exception ex) {
			System.err.println("# Error locating remote facade: " + ex);
		}
	}
	
	public static FacebookGateway getInstance() {
		if(instance == null) {
			instance = new FacebookGateway();
		}
		return instance;
	}
	
	public boolean facebookUserValidation(String email) {
		System.out.println("   - Verify email from Facebook Gateway");
		
		try {
			return this.facebookAuthorization.facebookUserValidation(email);
		} catch (Exception ex) {
			System.out.println("   $ Error verifying email: " + ex.getMessage());
			return false;
		}		
	}

	public boolean facebookPasswordValidation(String email, String password) {
		System.out.println("   - Verify password from Facebook Gateway");
		
		try {
			return this.facebookAuthorization.facebookPasswordValidation(email, password);
		} catch (Exception ex) {
			System.out.println("   $ Error verifying password: " + ex.getMessage());
			return false;
		}
	}

}