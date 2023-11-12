package es.deusto.ingenieria.sd.auctions.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.deusto.ingenieria.sd.auctions.server.data.domain.Article;
import es.deusto.ingenieria.sd.auctions.server.data.domain.Category;
import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.auctions.server.data.domain.TrainingSession;
import es.deusto.ingenieria.sd.auctions.server.data.domain.User;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ArticleAssembler;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ArticleDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.CategoryAssembler;
import es.deusto.ingenieria.sd.auctions.server.data.dto.CategoryDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.TrainingSessionDTO;
import es.deusto.ingenieria.sd.auctions.server.services.BidAppService;
import es.deusto.ingenieria.sd.auctions.server.services.LoginAppService;

public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade {	
	private static final long serialVersionUID = 1L;

	//Data structure for manage Server State
	private Map<Long, User> serverState = new HashMap<>();
	private List<ChallengeDTO> challengesDTO;

	private List<TrainingSessionDTO> trainingsDTO;
	//TODO: Remove this instances when Singleton Pattern is implemented
	private LoginAppService loginService = new LoginAppService();
	private MainAppService mianAppService = new MainAppService(); // To create on server services

	public RemoteFacade() throws RemoteException {
		super();		
	}
	
	@Override
	public synchronized long login(String email, String password) throws RemoteException {
		System.out.println(" * RemoteFacade login(): " + email + " / " + password);
				
		//Perform login() using LoginAppService
		User user = loginService.login(email, password);
			
		//If login() success user is stored in the Server State
		if (user != null) {
			//If user is not logged in 
			if (!this.serverState.values().contains(user)) {
				Long token = Calendar.getInstance().getTimeInMillis();		
				this.serverState.put(token, user);		
				return(token);
			} else {
				throw new RemoteException("User is already logged in!");
			}
		} else {
			throw new RemoteException("Login fails!");
		}
	}
	
	@Override
	public synchronized void logout(long token) throws RemoteException {
		System.out.println(" * RemoteFacade logout(): " + token);
		
		if (this.serverState.containsKey(token)) {
			//Logout means remove the User from Server State
			this.serverState.remove(token);
		} else {
			throw new RemoteException("User is not logged in!");
		}
	}
	
	@Override
	public void register(String account, String email, String name, Date birthDate, float weight, float height,
			int mBPM, int bpm) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ChallengeDTO> getChallenges() throws RemoteException {

		// TODO Auto-generated method stub

		return challengesDTO;

	}



	@Override

	public List<TrainingSessionDTO> getTrainingSessions() throws RemoteException {

		// TODO Auto-generated method stub

		return trainingsDTO;

	}



	@Override

	public boolean acceptChallenge(Challenge c) throws RemoteException {

		// TODO Auto-generated method stub

		

		if (c.getName() == null || c.getStart() == null || c.getEnd() == null || c.getSportType() == null) {

			return false;

		} else {

			return true;

		}

	}



	@Override

	public void createSession(String title, String sport, float distance, Date startDate, Time timeStart,

			float duration) throws RemoteException {

		// TODO Auto-generated method stub

		TrainingSession trainSes = new TrainingSession();

		

		trainSes.setTitle(title);

		trainSes.setSport(sport);

		trainSes.setDistance(distance);

		trainSes.setDuration(duration);

		trainSes.setStartDate(startDate);

		trainSes.setStartTime(timeStart);

	}



	@Override

	public boolean setupDistanceChallenge(String name, Date start, Date end, float metric, String sportType)

			throws RemoteException {

		// TODO Auto-generated method stub

		Challenge distCha = new Challenge();

		

		distCha.setName(name);

		distCha.setStart(start);

		distCha.setEnd(end);

		distCha.setMetric(metric);

		distCha.setSportType(sportType);

		

		if(distCha.getName() == null || distCha.getStart() == null || distCha.getEnd() == null || distCha.getSportType() == null) {

			return false;

		} else {

			return true;

		}

	}



	@Override

	public boolean setupActivityTimeChallenge(String name, Date start, Date end, float metric, String sportType)

			throws RemoteException {

		// TODO Auto-generated method stub

		Challenge distCha = new Challenge();

		

		distCha.setName(name);

		distCha.setStart(start);

		distCha.setEnd(end);

		distCha.setMetric(metric);

		distCha.setSportType(sportType);

		

		

		if(distCha.getName() == null || distCha.getStart() == null || distCha.getEnd() == null || distCha.getSportType() == null) {

			return false;

		} else {

			return true;

		}

	}
}