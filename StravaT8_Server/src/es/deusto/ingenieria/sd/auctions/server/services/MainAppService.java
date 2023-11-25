package es.deusto.ingenieria.sd.auctions.server.services;

import java.util.Date;
import java.time.LocalTime;
import java.util.List;

import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.auctions.server.data.domain.TrainingSession;

public class MainAppService {

	

	public MainAppService instance;

	public MainAppService() {};

	public List<Challenge> listaDistCha;

	public List<TrainingSession> listaTrainingSession;

	

	public boolean setUpDistanceChallenge(String name, Date start, Date end, Float metric, String sportType ) {

		

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

	

	public boolean setupActivityTimeChallenge(String name, Date start, Date end, Float metric, String sportType) {

		

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

	

	public List<Challenge> getChallenges() {

		return listaDistCha;

	}

	

	public boolean acceptChallenge(Challenge c) {

		

		if (c.getName() == null || c.getStart() == null || c.getEnd() == null || c.getSportType() == null) {

			return false;

		} else {

			return true;

		}

	}

	

	public void createSesion(String title, String sport, float distance, Date startDate, LocalTime startTime, int duration) {

		

		TrainingSession trainSes = new TrainingSession();

		

		trainSes.setTitle(title);

		trainSes.setSport(sport);

		trainSes.setDistance(distance);

		trainSes.setDuration(duration);

		trainSes.setStartDate(startDate);

		trainSes.setStartTime(startTime);

	}

	

	public List<TrainingSession> getTrainingSessions() {

		return listaTrainingSession;

	}

	

	public MainAppService getInstance() {

		

		MainAppService main = new MainAppService();

		return main;

	}	

}
