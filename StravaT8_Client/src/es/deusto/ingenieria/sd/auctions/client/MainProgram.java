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
		
		mainWindow.setupDistanceChallenge(loginController.getToken(), "olympics", new Date(), new Date(), (float)1000, "flying");
		mainWindow.setupActivityTimeChallenge(loginController.getToken(), "xGames", new Date(), new Date(), (float)60, "breath holding");
		System.out.println("Challenge accepted: " + mainWindow.acceptChallenge(loginController.getToken()));
		mainWindow.createSession(loginController.getToken(), "First Run", "running", (float)20, new Date(), LocalTime.now(), (float)10);
		
		mainWindow.getChallenges();
		mainWindow.getTrainingSession(loginController.getToken());
		
		//Logout
		loginDialog.logout();
	}
}