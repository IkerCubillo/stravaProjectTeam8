package es.deusto.ingenieria.sd.auctions.server.services;

import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

import es.deusto.ingenieria.sd.auctions.server.remote.RemoteFacade;
import es.deusto.ingenieria.sd.auctions.server.data.domain.TrainingSession;
import es.deusto.ingenieria.sd.auctions.server.data.domain.User;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dao.ChallengeDAO;
import es.deusto.ingenieria.sd.auctions.server.data.dao.TrainingSessionDAO;
import es.deusto.ingenieria.sd.auctions.server.data.dao.UserDAO;
import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge;

public class MainAppService {

	private static MainAppService instance;
	
	public MainAppService() { }
	
	public static MainAppService getInstance() {

		if(instance == null) {
			instance = new MainAppService();
		}
		return instance;
	}
	
	public List<Challenge> getChallenges() {
		return ChallengeDAO.getInstance().getAll();
	}
	
	public List<Challenge> getActiveChallenges(User user) {
		return new ArrayList<>(UserDAO.getInstance().find(user.getEmail()).getChallenges());
	}
	
	public boolean acceptChallenge(Challenge c) {
		try {
			ChallengeDAO.getInstance().save(c);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public List<TrainingSession> getTrainingSessions(User user) {
		return new ArrayList<>(UserDAO.getInstance().find(user.getEmail()).getTrainingSessions());
	}
	
	public boolean createSession(User user, String title, String sport, float distance, Date startDate,
			LocalTime startTime, float duration) {
		try {
			TrainingSession tr = new TrainingSession(title, sport, distance, startDate, startTime, duration, user);
			TrainingSessionDAO.getInstance().save(tr);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean setUpDistanceChallenge(String name, Date start, Date end, Float metric, String sportType) {

		Challenge distCha = new Challenge();

		distCha.setName(name);

		distCha.setStart(start);

		distCha.setEnd(end);

		distCha.setMetric(metric);

		distCha.setSportType(sportType);

		if (distCha.getName() == null || distCha.getStart() == null || distCha.getEnd() == null
				|| distCha.getSportType() == null) {

			return false;

		} else {
			ChallengeDAO.getInstance().save(distCha);
			return true;
		}

	}

	public boolean setupActivityTimeChallenge(String name, Date start, Date end, Float metric, String sportType) {

		Challenge actCha = new Challenge();

		actCha.setName(name);

		actCha.setStart(start);

		actCha.setEnd(end);

		actCha.setMetric(metric);

		actCha.setSportType(sportType);
		

		if (actCha.getName() == null || actCha.getStart() == null || actCha.getEnd() == null
				|| actCha.getSportType() == null) {

			return false;

		} else {
			ChallengeDAO.getInstance().save(actCha);
			return true;
		}

	}

	
	

	
}
