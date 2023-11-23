package es.deusto.ingenieria.sd.auctions.client.gui;



import java.util.List;


import es.deusto.ingenieria.sd.auctions.client.controller.MainController;
import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.auctions.server.data.domain.TrainingSession;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.TrainingSessionDTO;

//This clase simulates the GUI of the Bid use case
public class MainWindow {
	
	private MainController controller;
	private ChallengeDTO c;
	private TrainingSession t;
	

	
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
	
	public boolean setupDistanceChallenge() {
		System.out.println(" - Setting up distance challenge ...");
		
		boolean confirmation = this.controller.setupDistanceChallenge(c.getName(), c.getStart(), c.getEnd(), c.getMetric(), c.getSportType());
		
		return confirmation;
		
	}
	
	public boolean setupActivityTimeChallenge() {
		System.out.println(" - Setting up activity time challenge ...");
		
		boolean confirmation = this.controller.setupActivityTimeChallenge(c.getName(), c.getStart(), c.getEnd(), c.getMetric(), c.getSportType());
		
		return confirmation;
		
	}
	
	public boolean acceptChallenge() {
		System.out.println(" - Accepting challenge ...");
		
		boolean confirmation = this.controller.acceptChallenge(c);
		
		return confirmation;
	}
	
	public void createSession() {
		System.out.println(" - Creating session ...");
		
		this.controller.createSession(t.getTitle(), t.getSport(), t.getDistance(), t.getStartDate(), t.getStartTime(), t.getDuration());
	}
	
}
