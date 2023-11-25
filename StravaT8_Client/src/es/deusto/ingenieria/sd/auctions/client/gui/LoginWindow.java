package es.deusto.ingenieria.sd.auctions.client.gui;

import java.util.Date;

import es.deusto.ingenieria.sd.auctions.client.controller.LoginController;

public class LoginWindow {	
	private LoginController controller;	
	private String email = "thomas.e2001@gmail.com";
	private String password = "$!9PhNz,";
	private String account = "Google";
	private String name = "thomas.e2001@gmail.com";
	private Date birthdate = new Date(25,11,2023);
	private float weight = 80;
	private float height = 180;
	private int mbpm = 120;
	private int bpm = 70;

	public LoginWindow(LoginController controller) {
		this.controller = controller;
	}
	
	public boolean login() {		
		System.out.println(" - Login into the server: '" + this.email + "' - '" + this.password + "' ...");
		String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(password);
		System.out.println("\t* Password hash: " + sha1);		
		boolean result = this.controller.login(email, sha1);
		System.out.println("\t* Login result: " + result);
		System.out.println("\t* Token: " + this.controller.getToken());

		return result;
	}
	
	public void logout() {
		System.out.println(" - Logout from the server...");		
		this.controller.logout();
		System.out.println("\t* Logout success!");		

	}

	public void register() {
		System.out.println(" - Registering into the server: '" + this.account + "' - '" + this.email + "' - '" + this.password 
				+ "' - '" + this.name + "' - '" + this.birthdate + "' - '" + this.weight + "' - '" + this.height + "' - '" + this.mbpm 
				+ "' - '" + this.bpm + "' ...");
		this.controller.register(account, email, name, birthdate, weight, height, mbpm, bpm);
		System.out.println("\t* Register success!");
		
	}
}
