package es.deusto.ingenieria.sd.auctions.server.gateway;

public class GatewayFactory {
	
	public static IGateway createGateway(String account) {
		if (account.equals("Facebook")) {
			return FacebookGateway.getInstance();
		} else if (account.equals("Google")) {
			return GoogleGateway.getInstance();
		} else {
			return null;
		}
	}
}
