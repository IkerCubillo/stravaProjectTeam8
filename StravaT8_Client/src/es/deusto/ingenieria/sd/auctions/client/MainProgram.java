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
		public static ServiceLocator serviceLocator = new ServiceLocator();
		public static LoginController loginController = new LoginController(serviceLocator);
		public static LoginWindow loginDialog = new LoginWindow(loginController);
		public static MainController mainController = new MainController(serviceLocator);
		public static MainWindow mainWindow = new MainWindow(mainController);

	public static void main(String[] args) throws RemoteException {	
		
		//args[0] = RMIRegistry IP
		//args[1] = RMIRegistry Port
		//args[2] = Service Name
		serviceLocator.setService(args[0], args[1], args[2]);
		
		MainProgramGUI mpw = new MainProgramGUI();
		mpw.setVisible(true);
	}
}