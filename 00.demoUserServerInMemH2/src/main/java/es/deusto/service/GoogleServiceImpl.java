package es.deusto.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.deusto.remote.IGoogleAuthorization;

import java.rmi.RemoteException;
import java.util.Map;

@Service
public class GoogleServiceImpl implements IGoogleAuthorization {

    @Autowired
    private Map<String, String> userMap;

    @Override
    public boolean googleUserValidation(String email) throws RemoteException {
        if (this.userMap.containsKey(email)) {
            System.out.println("validated");
            return true;
        }
        return false;
    }

    @Override
    public boolean googlePasswordValidation(String email, String password) throws RemoteException {
        if (!googleUserValidation(email)) {
            System.err.println("  # Error in Google service: email does not exist");
            return false;
        } else {
            if (this.userMap.get(email).equals(password)) {
                return true;
            } else {
                System.err.println("  # Error in Google service: incorrect password for " + email);
                return false;
            }
        }
    }
}