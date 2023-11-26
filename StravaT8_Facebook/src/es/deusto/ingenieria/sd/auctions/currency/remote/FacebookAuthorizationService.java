package es.deusto.ingenieria.sd.auctions.currency.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class FacebookAuthorizationService extends UnicastRemoteObject implements IFacebookAuthorization {
	private static final long serialVersionUID = 1L;

	// Attribute for the Singleton pattern
	public static FacebookAuthorizationService instance;

	public Map<String, String> userMap = new HashMap<>();

	private FacebookAuthorizationService() throws RemoteException {
		super();
		initializeData();
	}

	private void initializeData() {
		userMap.put("asier@opendeusto.com", "password1");
		userMap.put("cubillo@opendeusto.com", "password3");
	}

	public static FacebookAuthorizationService getInstance() {
		if (instance == null) {
			try {
				instance = new FacebookAuthorizationService();
			} catch (Exception ex) {
				System.err.println("  # Error initializing service(): " + ex.getMessage());
			}
		}

		return instance;
	}

	@Override
	public boolean facebookUserValidation(String email) throws RemoteException {
		if (this.userMap.containsKey(email)) {
			System.out.println("validated: " + email);
			return true;
		}
		return false;
	}

	@Override
	public boolean facebookPasswordValidation(String email, String password) throws RemoteException {
		if (!facebookUserValidation(email)) {
			System.err.println("  # Error in Facebook service: email does not exist");
			return false;
		} else {
			if (this.userMap.get(email) == password) {
				return true;
			} else {
				System.err.println("  # Error in Facebook service: incorrect password for " + email);
				return false;
			}
		}
	}
}