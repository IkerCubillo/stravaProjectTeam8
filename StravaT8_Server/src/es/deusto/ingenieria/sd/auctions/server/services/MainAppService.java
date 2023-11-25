package es.deusto.ingenieria.sd.auctions.server.services;

import java.util.ArrayList;
import java.util.Date;
import java.time.LocalTime;
import java.util.List;

import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.auctions.server.data.domain.TrainingSession;
import es.deusto.ingenieria.sd.auctions.server.data.domain.User;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;

public class MainAppService {

	public MainAppService instance;

	public MainAppService() {
		this.initilizeData();
	};

	public List<Challenge> listChallenges = new ArrayList<Challenge>();

	// TODO: remove when DAO Pattern is implemented
	private void initilizeData() {
		// Create Users
		User user0 = new User();
		user0.setEmail("asier@opendeusto.com");
		user0.setPassword("password");
		user0.setAccount("Facebook");
		user0.setBirthDate(new Date(1 / 1 / 2003));
		user0.setWeight(180);
		user0.setHeight(180);
		user0.setmBPM(100);
		user0.setBpm(100);

		User user1 = new User();
		user1.setEmail("kerman@opendeusto.com");
		user1.setPassword("password");
		user1.setAccount("Google");
		user1.setBirthDate(new Date(1 / 1 / 2003));
		user1.setWeight(180);
		user1.setHeight(180);
		user1.setmBPM(100);
		user1.setBpm(100);

		User user2 = new User();
		user2.setEmail("cubillo@opendeusto.com");
		user2.setPassword("password");
		user2.setAccount("Facebook");
		user2.setBirthDate(new Date(1 / 1 / 2003));
		user2.setWeight(180);
		user2.setHeight(180);
		user2.setmBPM(100);
		user2.setBpm(100);

		// challenges
		Challenge cha1 = new Challenge();
		cha1.setName("Test1");
		cha1.setStart(new Date(1, 1, 2023));
		cha1.setEnd(new Date(1, 2, 2023));
		cha1.setMetric(100);
		cha1.setSportType("Swimming");
		listChallenges.add(cha1);

		Challenge cha2 = new Challenge();
		cha1.setName("Test1");
		cha1.setStart(new Date(2, 1, 2023));
		cha1.setEnd(new Date(2, 2, 2023));
		cha1.setMetric(200);
		cha1.setSportType("Flying");
		listChallenges.add(cha2);
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
			listChallenges.add(distCha);
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
			return true;
		}

	}

	public List<Challenge> getChallenges() {

		return listChallenges;
	}

	public boolean acceptChallenge(User user, Challenge c) {

		return user.acceptChallenge(c);
	}

	public boolean createSession(User user, String title, String sport, float distance, Date startDate,
			LocalTime startTime, float duration) {
		return user.createSession(title, sport, distance, startDate, startTime, duration);
	}

	public List<TrainingSession> getTrainingSessions(User user) {

		return user.getSessions();
	}

	public MainAppService getInstance() {

		MainAppService main = new MainAppService();

		return main;

	}

}
