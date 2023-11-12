package es.deusto.ingenieria.sd.auctions.client;

import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import es.deusto.ingenieria.sd.auctions.client.controller.MainController;
import es.deusto.ingenieria.sd.auctions.client.controller.LoginController;
import es.deusto.ingenieria.sd.auctions.client.gui.MainWindow;
import es.deusto.ingenieria.sd.auctions.client.gui.LoginWindow;
import es.deusto.ingenieria.sd.auctions.client.remote.ServiceLocator;
import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.TrainingSessionDTO;
import es.deusto.ingenieria.sd.auctions.server.remote.RemoteFacade;

public class MainProgram {

	public static void main(String[] args) throws RemoteException {	
		ServiceLocator serviceLocator = new ServiceLocator();
		RemoteFacade remoteFacade = new RemoteFacade();
		String name = null;
		Date start = null;
		Date end = null;
		float metric = 0;
		String sportType = null;
		Challenge c = null;
		
		//args[0] = RMIRegistry IP
		//args[1] = RMIRegistry Port
		//args[2] = Service Name
		serviceLocator.setService(args[0], args[1], args[2]);
		
		LoginController loginController = new LoginController(serviceLocator);
		LoginWindow loginDialog = new LoginWindow(loginController);			
		MainController mainController = new MainController(remoteFacade);			
		MainWindow mainWindow = new MainWindow(mainController);
		
		//Login
		loginDialog.login();		
		//Get Categories
		List<ChallengeDTO> challenges = mainWindow.getChallenges();
		//Get Articles of a category (first category is selected)
		List<TrainingSessionDTO> trainingSessions = mainWindow.getTrainingSession();
		//Convert currency to GBP
		mainWindow.setupDistanceChallenge(name, start, end, metric, sportType);
		//Convert currency to USD
		mainWindow.setupActivityTimeChallenge(name, start, end, metric, sportType);
		//Place a bid (first article of the category is selected; the token is stored in the BidController)
		mainWindow.acceptChallenge(c);
		String title = null;
		String sport = null;
		float distance = 0;
		Date startDate = null;
		Time timeStart = null;
		float duration = 0;
		mainWindow.createSession(title, sport, distance, startDate, timeStart, duration);
		//Logout
		loginDialog.logout();
	}
}