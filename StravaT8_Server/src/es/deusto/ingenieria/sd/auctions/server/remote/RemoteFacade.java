package es.deusto.ingenieria.sd.auctions.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.time.LocalTime;
import java.util.ArrayList;
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
	public Map<String, User> userMap = new HashMap<>();
	
	
	//TODO: Remove this instances when Singleton Pattern is implemented
	private LoginAppService loginService = new LoginAppService(); // To create on server services
	private MainAppService mainService = new MainAppService();

	public RemoteFacade() throws RemoteException {
		super();	
		this.initilizeData();
	}

	// TODO: remove when DAO Pattern is implemented
	private void initilizeData() throws RemoteException {
		// Create Users
		
		register("Facebook", "asier@opendeusto.com", "password1", "Asier", new Date(1 / 1 / 2003), 80, 180, 100, 100);
		register("Google", "kerman@opendeusto.com", "password2",  "Kerman", new Date(1 / 1 / 2003), 80, 180, 100, 100);
		register("Facebook", "cubillo@opendeusto.com", "password3",  "Iker", new Date(1 / 1 / 2003), 80, 180, 100, 100);
	}
	
	@Override
	public synchronized long login(String email, String password) throws RemoteException {
		System.out.println(" * RemoteFacade login(): " + email + " / " + password);
				
		//If login() success user is stored in the Server State
		if (this.userMap.containsKey(email)) {
			User user = this.userMap.get(email);
			// Check if password is correct
			boolean correctPassword = loginService.login(email, password);
			if(correctPassword) {
				//If user is not logged in 
				if (!this.serverState.values().contains(user)) {
					Long token = Calendar.getInstance().getTimeInMillis();		
					this.serverState.put(token, user);		
					return(token);
				} else {
					throw new RemoteException("User is already logged in!");
				}
			} else {
				throw new RemoteException("Password is not correct!");
			}			
		} else {
			throw new RemoteException("User doesn't exist! Try different email");
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
	
	public void register(String account, String email, String password, String name, Date birthDate, float weight, float height,
			int mBPM, int bpm) throws RemoteException {
		System.out.println(" * RemoteFacade register(): " + account + "' - '" + email 
				+ "' - '" + password + "' - '" + name + "' - '" + birthDate + "' - '" + weight + "' - '" + height + "' - '" + mBPM 
				+ "' - '" + bpm);
		
		//TODO: Perform register() using LoginAppService
		User user = new User();
		
		// creating user
		try {
			user.setAccount(account);
			user.setEmail(email);
			user.setPassword(password);
			user.setName(name);
			user.setBirthDate(birthDate);
			user.setWeight(weight);
			user.setHeight(height);
			user.setmBPM(mBPM);
			user.setBpm(bpm);
			
		} catch (Exception e) {
			throw new RemoteException("Register fails!");
		}
		
		// checking user exists in user map
		if (!this.userMap.containsKey(user.getEmail())) {	
			this.userMap.put(user.getEmail(), user);
			
			System.out.println(this.userMap);
		} else {
			throw new RemoteException("Email already on use!");
		}
	}

	public List<ChallengeDTO> getChallenges() throws RemoteException {

		System.out.println(" * RemoteFacade getChallenges()");
		
		List<Challenge> challenges = mainService.getChallenges();
		
		if (challenges != null) {
			//Convert domain object to DTO
			return ChallengeAssembler.getInstance().challengeToDTO(challenges);
		} else {
			throw new RemoteException("getChallenges() fails!");
		}

	}



	@Override

	public List<TrainingSessionDTO> getTrainingSessions(long token) throws RemoteException {

		if (this.serverState.containsKey(token)) {						
				return TrainingSessionAssembler.getInstance().trainingSessionToDTO(mainService.getTrainingSessions(this.serverState.get(token)));
		} else {
			throw new RemoteException("You must be logged in to see training sessions");
		}
	}



	public boolean acceptChallenge(long token, ChallengeDTO c) throws RemoteException {

		if (this.serverState.containsKey(token)) {						
			if (mainService.acceptChallenge(this.serverState.get(token), new Challenge(c))) {
				return true;
			} else {
				throw new RemoteException("acceptChallenge() fails!");
			}
		} else {
			throw new RemoteException("You must be logged in to accept a challenge");
		}
	}



	public boolean createSession(long token, String title, String sport, float distance, Date startDate, LocalTime timeStart, float duration) throws RemoteException {

		if (this.serverState.containsKey(token)) {						
			if (mainService.createSession(this.serverState.get(token), title, sport, distance, startDate, timeStart, duration)) {
				return true;
			} else {
				throw new RemoteException("createSession() fails!");
			}
		} else {
			throw new RemoteException("You must be logged in to create a session");
		}
	}



	public boolean setupDistanceChallenge(long token, String name, Date start, Date end, float metric, String sportType) throws RemoteException {

		if (this.serverState.containsKey(token)) {						
			if (mainService.setupActivityTimeChallenge(name, start, end, metric, sportType)) {
				return true;
			} else {
				throw new RemoteException("setupDistanceTimeChallenge() fails!");
			}
		} else {
			throw new RemoteException("You must be logged in to make an distance challenge");
		}
	}



	public boolean setupActivityTimeChallenge(long token, String name, Date start, Date end, float metric, String sportType) throws RemoteException {

		if (this.serverState.containsKey(token)) {						
			if (mainService.setupActivityTimeChallenge(name, start, end, metric, sportType)) {
				return true;
			} else {
				throw new RemoteException("setupActivityTimeChallenge() fails!");
			}
		} else {
			throw new RemoteException("You must be logged in to make an activity time challenge");
		}
	}
}