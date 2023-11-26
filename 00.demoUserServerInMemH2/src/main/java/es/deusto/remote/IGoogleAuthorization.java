package es.deusto.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGoogleAuthorization extends Remote {
    boolean googleUserValidation(String email) throws RemoteException;

    boolean googlePasswordValidation(String email, String password) throws RemoteException;
}
