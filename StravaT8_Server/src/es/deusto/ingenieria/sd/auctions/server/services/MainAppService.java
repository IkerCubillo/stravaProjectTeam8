package es.deusto.ingenieria.sd.auctions.server.services;

import java.util.ArrayList;
import java.util.Date;
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

	public List<Challenge> listChallenges = new ArrayList<Challenge>();

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
			listChallenges.add(distCha);
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

			listChallenges.add(actCha);
			ChallengeDAO.getInstance().save(actCha);
			return true;
		}

	}

	public List<Challenge> getChallenges() {

		return ChallengeDAO.getInstance().getAll();
	}
	
	public List<Challenge> getChallenge2() {
		
		return ChallengeDAO.getInstance().getAll();
	}

	public boolean acceptChallenge(User user, Challenge c) {

		return user.acceptChallenge(c);
	}

	public boolean createSession(User user, String title, String sport, float distance, Date startDate,
			LocalTime startTime, float duration) {
		return user.createSession(title, sport, distance, startDate, startTime, duration);
	}

	public List<TrainingSession> getTrainingSessions(User user) {

		return new ArrayList<>(UserDAO.getInstance().find(user.getEmail()).getSessions());
	}

	public static MainAppService getInstance() {

		if(instance == null) {
			instance = new MainAppService();
		}
		return instance;
	}

}
