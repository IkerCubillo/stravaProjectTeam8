package es.deusto.ingenieria.sd.auctions.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.deusto.ingenieria.sd.auctions.server.data.dao.UserDAO;
import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.auctions.server.data.domain.TrainingSession;
import es.deusto.ingenieria.sd.auctions.server.data.domain.User;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeAssembler;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.TrainingSessionAssembler;
import es.deusto.ingenieria.sd.auctions.server.data.dto.TrainingSessionDTO;
import es.deusto.ingenieria.sd.auctions.server.mailsender.MailSenderGateway;
import es.deusto.ingenieria.sd.auctions.server.services.LoginAppService;
import es.deusto.ingenieria.sd.auctions.server.services.MainAppService;

public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade {	
	private static final long serialVersionUID = 1L;

	//Data structure for manage Server State
	private Map<Long, User> serverState = new HashMap<>();	
	
	private LoginAppService loginService = LoginAppService.getInstance(); // To create on server services
	private MainAppService mainService = MainAppService.getInstance();

	public RemoteFacade() throws RemoteException {
		super();	
	}

	
	
	@Override
	public synchronized long login(String account, String email, String password) throws RemoteException {
		System.out.println(" * RemoteFacade login(): " + account + " / " + email + " / " + password);
				
		//If login() success user is stored in the Server State
		if (UserDAO.getInstance().find(email)!= null) { /// CHECK on DB
			User user = UserDAO.getInstance().find(email);
			
			// Check if password is correct
			boolean correctPassword = loginService.login(email, password, account);
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
	
	@Override
	public void register(String email, String name, String account, Date birthDate, float weight, float height,
			int mBPM, int bpm) throws RemoteException {
		System.out.println(" * RemoteFacade register(): " + account + "' - '" + email 
				+ "' - '"  + name + "' - '" + birthDate + "' - '" + weight + "' - '" + height + "' - '" + mBPM 
				+ "' - '" + bpm);
		
		// creating user
		try {			
			// checking user exists in user map
			loginService.register(email, account, name, birthDate, weight, height, mBPM, bpm);
		} catch (Exception e) {
			throw new RemoteException("Account email invalid");
		}
	}
	
	@Override
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
	public List<ChallengeDTO> getUserChallenges(long token) throws RemoteException {
		if (this.serverState.containsKey(token)) {		
			List<Challenge> challenges = mainService.getActiveChallenges(this.serverState.get(token));
			if (challenges != null) {
				for (Challenge challenge : challenges) {
					System.out.println("\t* " + challenge.getName());
				}
			} else {
				throw new RemoteException("getChallenges() fails!");
			}
			//Convert domain object to DTO
			return ChallengeAssembler.getInstance().challengeToDTO(challenges);
		} else {
			throw new RemoteException("You must be logged in to see your active challenges");
		}
	}
	
	@Override
	public boolean acceptChallenge(long token, ChallengeDTO c) throws RemoteException {
		if (this.serverState.containsKey(token)) {						
			if (mainService.acceptChallenge(c, this.serverState.get(token))) {
				return true;
			} else {
				throw new RemoteException("acceptChallenge() fails!");
			}
		} else {
			throw new RemoteException("You must be logged in to accept a challenge");
		}
	}

	@Override
	public List<TrainingSessionDTO> getTrainingSessions(long token) throws RemoteException {
		if (this.serverState.containsKey(token)) {		
			List<TrainingSession> TrainingSessions = mainService.getTrainingSessions(this.serverState.get(token));
			if(TrainingSessions != null) {
				for (TrainingSession trainingSession : TrainingSessions) {
					System.out.println("\t* " + trainingSession.getTitle());
				}
			} else {
				System.out.println("There is no Training Session");
			}
			if (TrainingSessions != null) {
				//Convert domain object to DTO
				return TrainingSessionAssembler.getInstance().trainingSessionToDTO(TrainingSessions);
			} else {
				throw new RemoteException("getTrainingSessions() fails!");
			}
		} else {
			throw new RemoteException("You must be logged in to see your training sessions");
		}
	}
	
	@Override
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

	@Override
	public boolean setUpDistanceChallenge(long token, String name, Date start, Date end, float metric, String sportType) throws RemoteException {

		if (this.serverState.containsKey(token)) {						
			if (mainService.setUpDistanceChallenge(name, start, end, metric, sportType, this.serverState.get(token))) {
				Challenge emailDetails = new Challenge(name, start, end, metric, sportType, this.serverState.get(token));
				new MailSenderGateway(this.serverState.get(token).getEmail()).sendMessage(emailDetails.toString());
				return true;
			} else {
				throw new RemoteException("setupDistanceTimeChallenge() fails!");
			}
		} else {
			throw new RemoteException("You must be logged in to make an distance challenge");
		}
	}

	@Override
	public boolean setUpActivityTimeChallenge(long token, String name, Date start, Date end, float metric, String sportType) throws RemoteException {

		if (this.serverState.containsKey(token)) {						
			if (mainService.setupActivityTimeChallenge(name, start, end, metric, sportType, this.serverState.get(token))) {
				Challenge emailDetails = new Challenge(name, start, end, metric, sportType, this.serverState.get(token));
				new MailSenderGateway(this.serverState.get(token).getEmail()).sendMessage(emailDetails.toString());
				return true;
			} else {
				throw new RemoteException("setupActivityTimeChallenge() fails!");
			}
		} else {
			throw new RemoteException("You must be logged in to make an activity time challenge");
		}
	}
}

