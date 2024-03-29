package es.deusto.ingenieria.sd.auctions.client.controller;

import java.rmi.RemoteException;
import java.sql.Date;

import es.deusto.ingenieria.sd.auctions.client.remote.ServiceLocator;

//This class implements Controller pattern.
public class LoginController {

	// Reference to the Service Locator
	private ServiceLocator serviceLocator;
	// This attibute stores the token when login success
	private long token = -1; // -1 = login has not been done or fails

	public LoginController(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public boolean login(String account, String email, String password) {
		try {
			this.token = this.serviceLocator.getService().login(account, email, password);
			return true;
		} catch (RemoteException e) {
			System.out.println("# Error during login: " + e);
			this.token = -1;
			return false;
		}
	}

	public void logout() {
		try {
			this.serviceLocator.getService().logout(this.token);
			this.token = -1;
		} catch (RemoteException e) {
			System.out.println("# Error during logout: " + e);
		}
	}

	public long getToken() {
		return token;
	}

	public boolean register(String email, String name, String account, Date birthdate, float weight, float height,
			int mbpm, int bpm) {
		try {
			this.serviceLocator.getService().register(email, name, account, birthdate, weight, height, mbpm, bpm);
			return true;
		} catch (RemoteException e) {
			System.out.println("# Error during registration: " + e);
			return false;
		}
	}
}