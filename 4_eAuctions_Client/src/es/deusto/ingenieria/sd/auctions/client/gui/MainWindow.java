package es.deusto.ingenieria.sd.auctions.client.gui;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import es.deusto.ingenieria.sd.auctions.client.controller.BidController;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.TrainingSessionDTO;

//This clase simulates the GUI of the Bid use case
public class MainWindow {
	
	private BidController controller;
	
	//This attributes are user for formatting currencies and dates
	private static NumberFormat CURRENCY_FORMATTER = NumberFormat.getCurrencyInstance(Locale.getDefault());
	private static DateFormat DATE_FORMATTER = DateFormat.getDateTimeInstance();
	
	public MainWindow(BidController bid) {
		this.controller = bid;
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
		
		List<TrainingSessionDTO> TrainingSessions = this.controller.getTrainingSession();
		
		for (TrainingSessionDTO TrainingSession : TrainingSessions) {
			System.out.println("\t* " + TrainingSession.getTitle());
		}
		
			
		return TrainingSessions;		
	}
	
	public boolean setupDistanceChallenge() {
		System.out.println(" - Setting up distance challenge ...");
		
		boolean confirmation = this.controller.setupDistanceChallenge();
		
		return confirmation;
		
	}
	
	public boolean setupActivityTimeChallenge() {
		System.out.println(" - Setting up activity time challenge ...");
		
		boolean confirmation = this.controller.setupActivityTimeChallenge();
		
		return confirmation;
		
	}
	
	public void acceptChallenge() {
		System.out.println(" - Accepting challenge ...");
		
		this.controller.acceptChallenge();
	}
	
	public void createSession() {
		System.out.println(" - Creating session ...");
		
		this.controller.creatingSession();
	}
	
}
