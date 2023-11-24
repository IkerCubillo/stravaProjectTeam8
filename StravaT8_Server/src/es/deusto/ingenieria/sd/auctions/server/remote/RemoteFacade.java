package es.deusto.ingenieria.sd.auctions.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.auctions.server.data.domain.TrainingSession;
import es.deusto.ingenieria.sd.auctions.server.data.domain.User1;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.TrainingSessionDTO;
import es.deusto.ingenieria.sd.auctions.server.services.LoginAppService;

public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade {	
	private static final long serialVersionUID = 1L;

	//Data structure for manage Server State
	private Map<Long, User1> serverState = new HashMap<>();
	private List<ChallengeDTO> challengesDTO;

	private List<TrainingSessionDTO> trainingsDTO;
	//TODO: Remove this instances when Singleton Pattern is implemented
	private LoginAppService loginService = new LoginAppService(); // To create on server services

	public RemoteFacade() throws RemoteException {
		super();		
	}
	
	@Override
	public synchronized long login(String email, String password) throws RemoteException {
		System.out.println(" * RemoteFacade login(): " + email + " / " + password);
				
		//Perform login() using LoginAppService
		User1 user = loginService.login(email, password);
			
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

		// TODO Auto-generated method stub

		return challengesDTO;

	}



	@Override

	public List<TrainingSessionDTO> getTrainingSessions() throws RemoteException {

		// TODO Auto-generated method stub

		return trainingsDTO;

	}



	public boolean acceptChallenge(ChallengeDTO c) throws RemoteException {

		// TODO Auto-generated method stub

		

		if (c.getName() == null || c.getStart() == null || c.getEnd() == null || c.getSportType() == null) {

			return false;

		} else {

			return true;

		}

	}



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


//	private static final long serialVersionUID = 1L;
//
//	//Data structure for manage Server State
//	private Map<Long, User> serverState = new HashMap<>();
//	
//	//TODO: Remove this instances when Singleton Pattern is implemented
//	private LoginAppService loginService = new LoginAppService();
//	private BidAppService bidService = new BidAppService();
//
//	public RemoteFacade() throws RemoteException {
//		super();		
//	}
//	
//	@Override
//	public synchronized long login(String email, String password) throws RemoteException {
//		System.out.println(" * RemoteFacade login(): " + email + " / " + password);
//				
//		//Perform login() using LoginAppService
//		User user = loginService.login(email, password);
//			
//		//If login() success user is stored in the Server State
//		if (user != null) {
//			//If user is not logged in 
//			if (!this.serverState.values().contains(user)) {
//				Long token = Calendar.getInstance().getTimeInMillis();		
//				this.serverState.put(token, user);		
//				return(token);
//			} else {
//				throw new RemoteException("User is already logged in!");
//			}
//		} else {
//			throw new RemoteException("Login fails!");
//		}
//	}
//	
//	@Override
//	public synchronized void logout(long token) throws RemoteException {
//		System.out.println(" * RemoteFacade logout(): " + token);
//		
//		if (this.serverState.containsKey(token)) {
//			//Logout means remove the User from Server State
//			this.serverState.remove(token);
//		} else {
//			throw new RemoteException("User is not logged in!");
//		}
//	}
//	
//	@Override
//	public List<CategoryDTO> getCategories() throws RemoteException {
//		System.out.println(" * RemoteFacade getCategories()");
//		
//		//Get Categories using BidAppService
//		List<Category> categories = bidService.getCategories();
//		
//		if (categories != null) {
//			//Convert domain object to DTO
//			return CategoryAssembler.getInstance().categoryToDTO(categories);
//		} else {
//			throw new RemoteException("getCategories() fails!");
//		}
//	}
//
//	@Override
//	public List<ArticleDTO> getArticles(String category) throws RemoteException {
//		System.out.println(" * RemoteFacade getArticle('" + category + "')");
//
//		//Get Articles using BidAppService
//		List<Article> articles = bidService.getArticles(category);
//		
//		if (articles != null) {
//			//Convert domain object to DTO
//			return ArticleAssembler.getInstance().articleToDTO(articles);
//		} else {
//			throw new RemoteException("getArticles() fails!");
//		}
//	}
//	
//	@Override
//	public boolean makeBid(long token, int article, float amount) throws RemoteException {		
//		System.out.println(" * RemoteFacade makeBid article : " + article + " / amount " + amount);
//		
//		if (this.serverState.containsKey(token)) {						
//			//Make the bid using Bid Application Service
//			if (bidService.makeBid(this.serverState.get(token), article, amount)) {
//				return true;
//			} else {
//				throw new RemoteException("makeBid() fails!");
//			}
//		} else {
//			throw new RemoteException("To place a bid you must first log in");
//		}
//	}
//
//	@Override
//	public float getUSDRate() throws RemoteException {
//		System.out.println(" * RemoteFacade get USD rate");
//
//		//Get rate using BidAppService
//		float rate = bidService.getUSDRate();
//		
//		if (rate != -1) {
//			return rate;
//		} else {
//			throw new RemoteException("getUSDRate() fails!");
//		}
//	}
//
//	@Override
//	public float getGBPRate() throws RemoteException {
//		System.out.println(" * RemoteFacade get GBP rate");
//		
//		//Get rate using BidAppService
//		float rate = bidService.getGBPRate();
//		
//		if (rate != -1) {
//			return rate;
//		} else {
//			throw new RemoteException("getGBPRate() fails!");
//		}
//	}
}