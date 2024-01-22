package es.deusto.ingenieria.sd.auctions.server.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.time.LocalTime;
import java.util.List;

import es.deusto.ingenieria.sd.auctions.server.data.domain.TrainingSession;
import es.deusto.ingenieria.sd.auctions.server.data.domain.User;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.mailsender.MailSenderGateway;
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
	
	public List<Float> getPercentages(User user) {
		List<Float> percentages = new ArrayList<>();
		
		List<Challenge> challenges = new ArrayList<>(UserDAO.getInstance().find(user.getEmail()).getChallenges());
		List<TrainingSession> trainingSessions = new ArrayList<>(UserDAO.getInstance().find(user.getEmail()).getTrainingSessions());
		for(Challenge c: challenges) {
			
			float goal = 0;
			if (c.getSportType().equals("Running")) {
				for(TrainingSession ts: trainingSessions) {
					if(ts.getSport().equals("Running")&&(ts.getStartDate().after(c.getStart()) && ts.getStartDate().before(c.getEnd()))) {
						goal += ts.getDistance();
					}
				}
			} else if (c.getSportType().equals("Cycling")){
				for(TrainingSession ts: trainingSessions) {
					if(ts.getSport().equals("Running")&&(ts.getStartDate().after(c.getStart()) && ts.getStartDate().before(c.getEnd()))) {
						goal += ts.getDistance();
					}
				}
			}else if (c.getSportType().equals("Both")) {
				for(TrainingSession ts: trainingSessions) {
					if((ts.getStartDate().after(c.getStart()) && ts.getStartDate().before(c.getEnd()))) {
						goal += ts.getDistance();
					}
				}
			}
			if(goal >= c.getMetric()) {
				user.removeChallenge(c);
				UserDAO.getInstance().save(user);
				ChallengeDAO.getInstance().delete(c);
			} else {
				float p = goal/c.getMetric();
				percentages.add(p);
			}
		}
		return percentages;
	}	
	
	public boolean acceptChallenge(ChallengeDTO c, User user) {
		try {
			c.setName(c.getName()+"_active_"+user.getEmail());
			Challenge challenge = new Challenge(c, user);
			ChallengeDAO.getInstance().save(challenge);
			user.addChallenge(challenge);
			UserDAO.getInstance().save(user);
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
			user.addTrainingSession(tr);
			UserDAO.getInstance().save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean setUpDistanceChallenge(String name, Date start, Date end, Float metric, String sportType, User user) {

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
			new MailSenderGateway(user.getEmail()).sendMessage(distCha.toEmail());
			return true;
		}
	}

	public boolean setupActivityTimeChallenge(String name, Date start, Date end, Float metric, String sportType, User user) {

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
			new MailSenderGateway(user.getEmail()).sendMessage(actCha.toEmail());
			return true;
		}
	}
}
