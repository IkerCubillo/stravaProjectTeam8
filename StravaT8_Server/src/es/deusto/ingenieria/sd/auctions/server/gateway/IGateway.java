package es.deusto.ingenieria.sd.auctions.server.gateway;

public interface IGateway {
	public boolean userValidation(String email);
	public boolean passwordValidation(String email, String password);
}
