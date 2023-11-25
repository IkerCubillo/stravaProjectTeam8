package es.deusto.ingenieria.sd.auctions.client;

import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import es.deusto.ingenieria.sd.auctions.client.controller.MainController;
import es.deusto.ingenieria.sd.auctions.client.controller.LoginController;
import es.deusto.ingenieria.sd.auctions.client.gui.MainWindow;
import es.deusto.ingenieria.sd.auctions.client.gui.LoginWindow;
import es.deusto.ingenieria.sd.auctions.client.remote.ServiceLocator;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.TrainingSessionDTO;

public class MainProgram {

	public static void main(String[] args) throws RemoteException {	
		ServiceLocator serviceLocator = new ServiceLocator();

		
		//args[0] = RMIRegistry IP
		//args[1] = RMIRegistry Port
		//args[2] = Service Name
		serviceLocator.setService(args[0], args[1], args[2]);
		
		LoginController loginController = new LoginController(serviceLocator);
		LoginWindow loginDialog = new LoginWindow(loginController);			
		MainController mainController = new MainController(serviceLocator);			
		MainWindow mainWindow = new MainWindow(mainController);
		
		loginDialog.register();
		//Login
		loginDialog.login();		
		
		mainWindow.setupDistanceChallenge("olympics", new Date(), new Date(), (float)1000, "flying");
		mainWindow.setupActivityTimeChallenge("xGames", new Date(), new Date(), (float)60, "breath holding");
		//System.out.println("Challenge accepted: " + mainWindow.acceptChallenge());
		mainWindow.createSession("First Run", "running", (float)20, new Date(), LocalTime.now(), (float)10);
		
//		List<ChallengeDTO> challenges = mainWindow.getChallenges();
//		for (ChallengeDTO challenge : challenges) {
//			System.out.println("\t* " + challenge.getName());
//		}
//		List<TrainingSessionDTO> trainingSessions = mainWindow.getTrainingSession();
//		for (TrainingSessionDTO TrainingSession : trainingSessions) {
//			System.out.println("\t* " + TrainingSession.getTitle());
//		}
		
		//Logout
		loginDialog.logout();
	}
}