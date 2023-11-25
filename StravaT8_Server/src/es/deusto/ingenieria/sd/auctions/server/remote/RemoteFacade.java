package es.deusto.ingenieria.sd.auctions.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.auctions.server.data.domain.TrainingSession;
import es.deusto.ingenieria.sd.auctions.server.data.domain.User;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeAssembler;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.TrainingSessionAssembler;
import es.deusto.ingenieria.sd.auctions.server.data.dto.TrainingSessionDTO;
import es.deusto.ingenieria.sd.auctions.server.services.LoginAppService;
import es.deusto.ingenieria.sd.auctions.server.services.MainAppService;

public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade {	
	private static final long serialVersionUID = 1L;

	//Data structure for manage Server State
	private Map<Long, User> serverState = new HashMap<>();
	private List<ChallengeDTO> challengesDTO;

	private List<TrainingSessionDTO> trainingsDTO;
	//TODO: Remove this instances when Singleton Pattern is implemented
	private LoginAppService loginService = new LoginAppService(); // To create on server services
	private MainAppService mainService = new MainAppService();

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
	
	public void register(String account, String email, String name, Date birthDate, float weight, float height,
			int mBPM, int bpm) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public List<ChallengeDTO> getChallenges() throws RemoteException {

		System.out.println(" * RemoteFacade getCategories()");
		
		List<Challenge> challenges = mainService.getChallenges();
		
		if (challenges != null) {
			//Convert domain object to DTO
			return ChallengeAssembler.getInstance().challengeToDTO(challenges);
		} else {
			throw new RemoteException("getChallenges() fails!");
		}


	}



	@Override

	public List<TrainingSessionDTO> getTrainingSessions() throws RemoteException {

		System.out.println(" * RemoteFacade getArticle()");

		//Get Articles using BidAppService
		List<TrainingSession> trainingSession = mainService.getTrainingSessions();
		
		if (trainingSession != null) {
			//Convert domain object to DTO
			return TrainingSessionAssembler.getInstance().trainingSessionToDTO(trainingSession);
		} else {
			throw new RemoteException("getTrainingSession() fails!");
		}

	}



	public boolean acceptChallenge(ChallengeDTO c) throws RemoteException {

		// TODO Auto-generated method stub

		

		if (c.getName() == null || c.getStart() == null || c.getEnd() == null || c.getSportType() == null) {

			return false;

		} else {

			return true;

		}

	}



	public void createSession(String title, String sport, float distance, Date startDate, LocalTime timeStart,

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