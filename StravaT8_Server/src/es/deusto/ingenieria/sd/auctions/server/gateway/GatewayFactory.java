package es.deusto.ingenieria.sd.auctions.server.gateway;

public class GatewayFactory {
	
	public static IGateway createGateway(String account) {
		if (account.equals("Facebook")) {
			return new FacebookGateway();
		} else if (account.equals("Google")) {
			return new GoogleGateway();
		} else {
			return null;
		}
	}
}
