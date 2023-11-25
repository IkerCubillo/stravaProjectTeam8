package es.deusto.ingenieria.sd.auctions.client.gui;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import es.deusto.ingenieria.sd.auctions.client.controller.MainController;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.TrainingSessionDTO;

//This clase simulates the GUI of the Bid use case
public class MainWindow {
	
	private MainController controller;
	private ChallengeDTO c;
	private TrainingSessionDTO t;
	

	
	public MainWindow(MainController main) {
		this.controller = main;
	}

	public List<ChallengeDTO> getChallenges() {
		System.out.println(" - Getting all the challenges ...");
		
		List<ChallengeDTO> challenges = this.controller.getChallenges();
		
		for (ChallengeDTO challenge : challenges) {
			System.out.println("\t* " + challenge.getName());
		}
		
		return challenges;
	}

	public List<TrainingSessionDTO> getTrainingSession() { 		
		System.out.println(" - Getting training sessions '" + "' ...");
		
		List<TrainingSessionDTO> TrainingSessions = this.controller.getTrainingSessions();
		
		for (TrainingSessionDTO TrainingSession : TrainingSessions) {
			System.out.println("\t* " + TrainingSession.getTitle());
		}
		
			
		return TrainingSessions;		
	}
	
	public boolean setupDistanceChallenge(String name, Date start, Date end, Float metric, String sportType ) {
		System.out.println(" - Setting up distance challenge ...");
		
		boolean confirmation = this.controller.setupDistanceChallenge(name, start, end, metric, sportType);
		
		return confirmation;
		
	}
	
	public boolean setupActivityTimeChallenge(String name, Date start, Date end, Float metric, String sportType) {
		System.out.println(" - Setting up activity time challenge ...");
		
		boolean confirmation = this.controller.setupActivityTimeChallenge(name, start, end, metric, sportType);
		
		return confirmation;
		
	}
	
	public boolean acceptChallenge() {
		System.out.println(" - Accepting challenge ...");
		
		//for now, hay que cambiar a challenge eligido
		boolean confirmation = this.controller.acceptChallenge(getChallenges().get(0));
		
		return confirmation;
	}
	
	public void createSession(String title, String sport, float distance, Date startDate, LocalTime timeStart, float duration) {
		System.out.println(" - Creating session ...");
		
		this.controller.createSession(title, sport, distance, startDate, timeStart, duration);
	}
	
}
