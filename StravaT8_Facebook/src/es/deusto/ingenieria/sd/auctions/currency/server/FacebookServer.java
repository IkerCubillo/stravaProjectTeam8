package es.deusto.ingenieria.sd.auctions.currency.server;

import java.rmi.Naming;

import es.deusto.ingenieria.sd.auctions.currency.remote.FacebookAuthorizationService;
import es.deusto.ingenieria.sd.auctions.currency.remote.IFacebookAuthorization;

public class FacebookServer {

	public static void main(String[] args) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];		
		
		try {
			IFacebookAuthorization remoteObject = FacebookAuthorizationService.getInstance();			
			Naming.rebind(name, remoteObject);
			System.out.println(" * Facebook Server '" + name + "' started!!");
		} catch (Exception ex) {
			System.out.println(" # Facebook Server: " + ex.getMessage());
			ex.printStackTrace();
		}

	}
}