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
		List<ChallengeDTO> challenges = mainWindow.getChallenges();
		for (ChallengeDTO challenge : challenges) {
			System.out.println("\t* " + challenge.getName());
		}
		List<TrainingSessionDTO> trainingSessions = mainWindow.getTrainingSession();
		for (TrainingSessionDTO TrainingSession : trainingSessions) {
			System.out.println("\t* " + TrainingSession.getTitle());
		}
		mainWindow.setupDistanceChallenge();
		mainWindow.setupActivityTimeChallenge();
		mainWindow.acceptChallenge();
		mainWindow.createSession();
		//Logout
		loginDialog.logout();
	}
}