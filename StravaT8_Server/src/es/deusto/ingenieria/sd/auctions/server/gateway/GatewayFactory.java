package es.deusto.ingenieria.sd.auctions.server.gateway;

public class GatewayFactory {
	
	private static GatewayFactory instance;
	
	public static IGateway createGateway(String account) {
		if (account.equals("Facebook")) {
			return FacebookGateway.getInstance();
		} else if (account.equals("Google")) {
			return GoogleGateway.getInstance();
		} else {
			return null;
		}
	}
	
	public static GatewayFactory getInstance() {
		if (instance == null) {
			instance = new GatewayFactory();
		}
		return instance;
	}
	
}
