package es.deusto.ingenieria.sd.auctions.client.gui;

import java.sql.Date;

import es.deusto.ingenieria.sd.auctions.client.controller.LoginController;

public class LoginWindow {	
	private LoginController controller;	

	public LoginWindow(LoginController controller) {
		this.controller = controller;
	}
	
	public boolean login(String account, String email, String password) {		
		System.out.println(" - Login into the server: '" + account + "' - '" + email + "' - '" + password + "' ...");
//		String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(password);
//		System.out.println("\t* Password hash: " + sha1);		
		boolean result = this.controller.login(account, email, password);
		System.out.println("\t* Login result: " + result);
		System.out.println("\t* Token: " + this.controller.getToken());

		return result;
	}
	
	public void logout() {
		System.out.println(" - Logout from the server...");		
		this.controller.logout();
		System.out.println("\t* Logout success!");		

	}

	public void register(String email, String name, String account, Date birthdate, float weight, float height, int mBPM, int BPM) {
		System.out.println(" - Registering into the server: '" + email + "' - '" + name
				+ "' - '" + account + "' - '" + birthdate + "' - '" + weight + "' - '" + height + "' - '" + mBPM 
				+ "' - '" + BPM + "' ...");
		this.controller.register(email, name, account, birthdate, weight, height, mBPM, BPM);
		System.out.println("\t* Registration success!");
	}
}
