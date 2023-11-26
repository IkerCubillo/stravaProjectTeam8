package es.deusto.ingenieria.sd.auctions.currency.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IFacebookAuthorization extends Remote {
	public boolean facebookUserValidation(String email) throws RemoteException;
	public boolean facebookPasswordValidation(String email, String password) throws RemoteException;
}
