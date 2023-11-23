package es.deusto.ingenieria.sd.auctions.client.controller;

import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;


import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.TrainingSessionDTO;
import es.deusto.ingenieria.sd.auctions.client.remote.ServiceLocator;

//This class implements Controller pattern.
public class MainController{
	
	//Reference to the Service Locator
	private ServiceLocator ServiceLocator;
	
	public MainController(ServiceLocator ServiceLocator) {
		this.ServiceLocator = ServiceLocator; 
	}

	public List<ChallengeDTO> getChallenges() {
		try {
			return this.ServiceLocator.getService().getChallenges();
		} catch (RemoteException e) {
			System.out.println("# Error getting all challenges: " + e);
			return null;
		}
	}

	public List<TrainingSessionDTO> getTrainingSessions() {
		try {
			return this.ServiceLocator.getService().getTrainingSessions();
		} catch (RemoteException e) {
			System.out.println("# Error getting articles of a category: " + e);
			return null;
		}
	}

	public boolean setupDistanceChallenge(String name, Date start, Date end, float metric, String sportType) {
		try {
			return this.ServiceLocator.getService().setupDistanceChallenge(name, start, end, metric, sportType);
		} catch (RemoteException e) {
			System.out.println("# Error making a bid: " + e);
			return false;
		}
	}
	
	public boolean setupActivityTimeChallenge(String name, Date start, Date end, float metric, String sportType) {
		try {
			return this.ServiceLocator.getService().setupActivityTimeChallenge(name, start, end, metric, sportType);
		} catch (RemoteException e) {
			System.out.println("# Error getting USD rate: " + e);
			return false;
		}
	}
	
	public boolean acceptChallenge(Challenge c) {
		try {
			return this.ServiceLocator.getService().acceptChallenge(c);
		} catch (RemoteException e) {
			System.err.println("# Error getting GBP rate: " + e);	
			return false;
		}
	}
	
	public void createSession(String title, String sport, float distance, Date startDate, Time timeStart, float duration) {
		try {
			this.ServiceLocator.getService().createSession(title, sport, distance, startDate, timeStart, duration);;
		} catch (RemoteException e) {
			System.err.println("# Error getting GBP rate: " + e);
		}
	}

	
}
