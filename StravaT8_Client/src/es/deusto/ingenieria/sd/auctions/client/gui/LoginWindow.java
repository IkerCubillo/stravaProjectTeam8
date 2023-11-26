package es.deusto.ingenieria.sd.auctions.client.gui;

import java.util.Date;

import es.deusto.ingenieria.sd.auctions.client.controller.LoginController;

public class LoginWindow {	
	private LoginController controller;	
	private String email = "fletch@opendeusto.es";
	private String password = "password4";
	private String account = "Google";
	private String name = "fletch";
	private Date birthdate = new Date(25,11,2023);
	private float weight = 80;
	private float height = 180;
	private int mbpm = 120;
	private int bpm = 70;

	public LoginWindow(LoginController controller) {
		this.controller = controller;
	}
	
	//temporarily uses predefined user
	public boolean login(String email, String password) {		
		System.out.println(" - Login into the server: '" + email + "' - '" + password + "' ...");
//		String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(password);
//		System.out.println("\t* Password hash: " + sha1);		
		boolean result = this.controller.login(email, password);
		System.out.println("\t* Login result: " + result);
		System.out.println("\t* Token: " + this.controller.getToken());

		return result;
	}
	
	public void logout() {
		System.out.println(" - Logout from the server...");		
		this.controller.logout();
		System.out.println("\t* Logout success!");		

	}

	//temporarily uses predefined user
	public void register(String account, String email, String name, Date birthdate, float weight, float height, int mBPM, int BPM) {
		System.out.println(" - Registering into the server: '" + account + "' - '" + email
				+ "' - '" + name + "' - '" + birthdate + "' - '" + weight + "' - '" + height + "' - '" + mBPM 
				+ "' - '" + BPM + "' ...");
		this.controller.register(account, email, name, birthdate, weight, height, mBPM, BPM);
		System.out.println("\t* Registration success!");
		
	}
}
