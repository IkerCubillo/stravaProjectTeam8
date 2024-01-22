package es.deusto.ingenieria.sd.auctions.server.services;


import es.deusto.ingenieria.sd.auctions.server.gateway.IGateway;

import java.rmi.RemoteException;
import java.util.Date;

import es.deusto.ingenieria.sd.auctions.server.data.dao.UserDAO;
import es.deusto.ingenieria.sd.auctions.server.data.domain.User;
import es.deusto.ingenieria.sd.auctions.server.gateway.GatewayFactory;

//TODO: Implement Singleton Pattern
public class LoginAppService {
	
	private static LoginAppService instance;
		
	public boolean login(String email, String password, String account) {
		//TODO: Get User using DAO and check 
		User user = UserDAO.getInstance().find(email);		
		if (user != null) {
			IGateway g = GatewayFactory.createGateway(account);
			return g.passwordValidation(email,password);
		} else {
			return false;
		}
		
	}
	
	public void register(String email, String name, String account,  Date birthDate, float weight, float height, int mBPM, int bpm) throws RemoteException {		
		IGateway g = GatewayFactory.createGateway(account);		
		// checking user exists in user map
		if(g.userValidation(email)) {
			User user = new User(email, name, account, birthDate, weight, height, mBPM, bpm);
			if (UserDAO.getInstance().find(email)== null) { /// CHECK on DB
				UserDAO.getInstance().save(user);
			} else {
				throw new RemoteException("Email already in use!");
			}
		} else {
			throw new RemoteException("Account doesn't exist");
		}
		
		
	}
	
	public static LoginAppService getInstance() {

		if(instance == null) {
			instance = new LoginAppService();
		}
		return instance;
	}
}