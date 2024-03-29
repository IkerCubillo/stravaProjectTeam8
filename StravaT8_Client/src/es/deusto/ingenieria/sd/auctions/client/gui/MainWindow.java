package es.deusto.ingenieria.sd.auctions.client.gui;

import java.time.LocalTime;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.List;

import es.deusto.ingenieria.sd.auctions.client.controller.MainController;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.TrainingSessionDTO;

//This class simulates the GUI of the Bid use case
public class MainWindow {

	private MainController controller;

	public MainWindow(MainController main) {
		this.controller = main;
	}

	public List<ChallengeDTO> getChallenges() {
		System.out.println(" - Getting all the challenges ...");

		List<ChallengeDTO> challenges = this.controller.getChallenges();
		
		if(challenges != null) {
			for (ChallengeDTO challenge : challenges) {
				System.out.println("\t* " + challenge.getName());
				System.out.println(challenge.toString()); 
			}
		} else {
			System.out.println("There is no challenge");
		}

		return challenges;
	}
	
	public List<ChallengeDTO> getUserChallenges(long token) {
		System.out.println(" - Getting all the challenges ...");
		
		List<ChallengeDTO> challenges = this.controller.getUserChallenges(token);
		
		if(challenges != null) {
			for (ChallengeDTO challenge : challenges) {
				System.out.println("\t* " + challenge.getName());
				System.out.println(challenge.toString()); 
			}
		} else {
			System.out.println("There is no active challenge");
		}
		
		return challenges;
	}
	
	public List<Float> getPercentages(long token){
		System.out.println(" - Getting all the percentages ...");
		
		List<Float> percentages = this.controller.getPercentages(token);
		
		if(percentages != null) {
			for (Float p : percentages) {
				System.out.println("\t* " + p);
			}
		} else {
			System.out.println("There is no active challenge");
		}
		
		return percentages;
	}

	public List<TrainingSessionDTO> getTrainingSession(long token) {
		System.out.println(" - Getting training sessions '" + "' ...");

		List<TrainingSessionDTO> TrainingSessions = this.controller.getTrainingSessions(token);
		
		if(TrainingSessions != null) {
			for (TrainingSessionDTO trainingSession : TrainingSessions) {
				System.out.println("\t* " + trainingSession.getTitle());
				System.out.println(trainingSession.toString()); 
			}
		} else {
			System.out.println("There is no Training Session");
		}

		return TrainingSessions;
	}

	public boolean setupDistanceChallenge(long token, String name, Date start, Date end, Float metric,
			String sportType) {
		
		System.out.println(" - Setting up distance challenge ...");
		return this.controller.setupDistanceChallenge(token, name, start, end, metric, sportType);

	}

	public boolean setupActivityTimeChallenge(long token, String name, Date start, Date end, Float metric,
			String sportType) {

		System.out.println(" - Setting up activity time challenge ...");
		return this.controller.setupActivityTimeChallenge(token, name, start, end, metric, sportType);

	}

	public boolean acceptChallenge(long token, ChallengeDTO c) {

		System.out.println(" - Accepting challenge ...");
		return this.controller.acceptChallenge(token, c);
	}

	public void createSession(long token, String title, String sport, float distance, Date startDate,
			LocalTime timeStart, float duration) {

		System.out.println(" - Creating session ...");
		this.controller.createSession(token, title, sport, distance, startDate, timeStart, duration);
	}

}
