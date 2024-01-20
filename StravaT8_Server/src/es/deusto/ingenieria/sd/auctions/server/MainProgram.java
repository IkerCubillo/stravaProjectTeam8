package es.deusto.ingenieria.sd.auctions.server;

import java.rmi.Naming;
import java.time.LocalTime;
import java.util.Date;

import es.deusto.ingenieria.sd.auctions.server.data.dao.ChallengeDAO;
import es.deusto.ingenieria.sd.auctions.server.data.dao.TrainingSessionDAO;
import es.deusto.ingenieria.sd.auctions.server.data.dao.UserDAO;
import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.auctions.server.data.domain.TrainingSession;
import es.deusto.ingenieria.sd.auctions.server.data.domain.User;
import es.deusto.ingenieria.sd.auctions.server.remote.IRemoteFacade;
import es.deusto.ingenieria.sd.auctions.server.remote.RemoteFacade;

public class MainProgram {

	private static int socketPort;
	private static String serverIP;

	@SuppressWarnings({ "removal", "deprecation" })
	public static void main(String[] args) {
		// Activate Security Manager. It is needed for RMI.
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		// args[0] = RMIRegistry IP
		// args[1] = RMIRegistry Port
		// args[2] = Service Name
		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
		socketPort = Integer.parseInt(args[3]);
		serverIP = args[0];
		
		initDB();
		
		// Bind remote facade instance to a sirvice name using RMIRegistry
		try {
			IRemoteFacade remoteFacade = new RemoteFacade();
			Naming.rebind(name, remoteFacade);
			System.out.println(" * Strava Server v1 '" + name + "' started!!");
		} catch (Exception ex) {
			System.err.println(" # Strava Server Exception: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public static void initDB() {
		try { 
			//Create Users
			User user0 = new User();
			user0.setEmail("asier.castrillejo@opendeusto.es");
			user0.setName("Asier");
			user0.setAccount("Facebook");
			user0.setBirthDate(new Date(1 / 1 / 2003));
			user0.setWeight(80);
			user0.setHeight(180);
			user0.setBpm(100);
			user0.setmBPM(100);
			user0.setPassword("password");			
							
			User user1 = new User();
			user1.setEmail("kerman.bruna@opendeusto.es");
			user1.setName("Kerman");
			user1.setAccount("Google");
			user1.setBirthDate(new Date(1 / 1 / 2003));
			user1.setWeight(80);
			user1.setHeight(180);
			user1.setBpm(100);
			user1.setmBPM(100);
			user1.setPassword("password");
			
			User user2 = new User();
			user2.setEmail("i.cubillo@opendeusto.es");
			user2.setName("Iker");
			user2.setAccount("Facebook");
			user2.setBirthDate(new Date(1 / 1 / 2003));
			user2.setWeight(80);
			user2.setHeight(180);
			user2.setBpm(100);
			user2.setmBPM(100);
			user2.setPassword("password");			
			
			//Store Users in the DB
			UserDAO.getInstance().save(user0);
			UserDAO.getInstance().save(user1);
			UserDAO.getInstance().save(user2);
			
			//Create challenges
			Challenge c1 = new Challenge();
			c1.setName("Challenge 1");
			c1.setStart(new Date(15 / 2 / 2024));
			c1.setEnd(new Date(17 / 2 / 2024));
			c1.setMetric(60);
			c1.setSportType("Running");
			Challenge c2 = new Challenge();
			c2.setName("Challenge 2");
			c2.setStart(new Date(25 / 2 / 2024));
			c2.setEnd(new Date(25 / 2 / 2024));
			c2.setMetric(30);
			c2.setSportType("Cycling");
			Challenge c3 = new Challenge();
			c3.setName("Challenge 3");
			c3.setStart(new Date(25 / 2 / 2024));
			c3.setEnd(new Date(26 / 2 / 2024));
			c3.setMetric(100);
			c3.setSportType("Both");		
			Challenge c4 = new Challenge();
			c4.setName("Challenge 4");
			c4.setStart(new Date(27 / 2 / 2024));
			c4.setEnd(new Date(27 / 2 / 2024));
			c4.setMetric(40);
			c4.setSportType("Cycling");
			Challenge c5 = new Challenge();
			c5.setName("Challenge 5");
			c5.setStart(new Date(1 / 3 / 2024));
			c5.setEnd(new Date(1 / 3 / 2024));
			c5.setMetric(60);
			c5.setSportType("Running");
			
			//Store challenges in the DB
			ChallengeDAO.getInstance().save(c1);
			ChallengeDAO.getInstance().save(c2);
			ChallengeDAO.getInstance().save(c3);
			ChallengeDAO.getInstance().save(c4);
			ChallengeDAO.getInstance().save(c5);
			
			//Create training sessions
			TrainingSession t1 = new TrainingSession();
			t1.setTitle("Session 1");
			t1.setSport("Running");
			t1.setDistance(30);
			t1.setStartDate(new Date(15 / 2 / 2024));
			LocalTime lt1 = LocalTime.of(15, 20, 45);
			t1.setStartTime(lt1);
			t1.setDuration(60);
			TrainingSession t2 = new TrainingSession();
			t2.setTitle("Session 2");
			t2.setSport("Cycling");
			t2.setDistance(50);
			t2.setStartDate(new Date(16 / 2 / 2024));
			LocalTime lt2 = LocalTime.of(18, 30, 0);
			t2.setStartTime(lt2);
			t2.setDuration(90);
			TrainingSession t3 = new TrainingSession();
			t3.setTitle("Session 3");
			t3.setSport("Running");
			t3.setDistance(100);
			t3.setStartDate(new Date(15 / 2 / 2024));
			LocalTime lt3 = LocalTime.of(12, 0, 0);
			t3.setStartTime(lt3);
			t3.setDuration(60);
			
			//Store sessions in the DB
			TrainingSessionDAO.getInstance().save(t1);
			TrainingSessionDAO.getInstance().save(t2);
			TrainingSessionDAO.getInstance().save(t3);
								
		} catch (Exception ex) {
			System.out.println("\t$ Error storing data:" + ex.getMessage());
		}			
	}
	
	public static int getSocketPort() {
		return socketPort;
	}

	public static String getServerIP() {
		return serverIP;
	}

}
