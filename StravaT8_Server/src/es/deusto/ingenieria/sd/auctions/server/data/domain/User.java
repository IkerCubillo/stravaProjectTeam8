package es.deusto.ingenieria.sd.auctions.server.data.domain;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import es.deusto.ingenieria.sd.auctions.server.data.dao.TrainingSessionDAO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;

@Entity
public class User {
	
	@Id
	private String email;
	private String name;
	private String account;
	private Date birthDate;
	private float weight;
	private float height;
	private int mBPM;
	private int bpm;
	

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	private Set<TrainingSession> userSessions = new HashSet<>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	private Set<Challenge> userActiveChallenges = new HashSet<>();
	
	public User(String email, String name, String account, Date birthDate, float weight, float height, int mBPM, int bpm) {
		this.email = email;
		this.name = name;
		this.account = account;
		this.birthDate = birthDate;
		this.weight = weight;
		this.height = height;
		this.mBPM = mBPM;
		this.bpm = bpm;
	}
	
	public User() {
		
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public float getWeight() {
		return weight;
	}
	
	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getmBPM() {
		return mBPM;
	}

	public void setmBPM(int mBPM) {
		this.mBPM = mBPM;
	}

	public float getBpm() {
		return bpm;
	}

	public void setBpm(int bpm) {
		this.bpm = bpm;
	}
	
	public Set<TrainingSession> getTrainingSessions() {
		return userSessions;
	}
	
	public void setTrainingSessions(Set<TrainingSession> trainingSession) {
		this.userSessions = trainingSession;
	}
	
	public void addTrainingSession(TrainingSession trainingSession) {
		if (userSessions != null) {
			this.userSessions.add(trainingSession);
		}
	}
	
	public Set<Challenge> getChallenges() {
		return userActiveChallenges;
	}
	
	public void setChallenges(Set<Challenge> challenge) {
		this.userActiveChallenges = challenge;
	}
	
	public void addChallenge(Challenge challenge) {
		if (userActiveChallenges != null) {
			this.userActiveChallenges.add(challenge);
		}
	}
	
//	public ArrayList<TrainingSession> getSessions() {
//		return userSessions;
//	}
//
//	//takes in challenge and adds it if the user doesn't already have it
//	public boolean createSession(String title, String sport, float distance, Date startDate, LocalTime timeStart, float duration) {
//		TrainingSession tr = new TrainingSession(title, sport, distance, startDate, timeStart, duration, this);
//		TrainingSessionDAO.getInstance().save(tr);
//		return userSessions.add(tr);
//	}
//	
//	public boolean acceptChallenge(Challenge c) {
//		for (Challenge temp : userChallenges) {
//			if (c.equals(temp)) {
//				return false;
//			}
//		}
//		userChallenges.add(c);
//		return true;
//	}
//	
//	public static ArrayList<Challenge> getUserChallenges() {
//		ArrayList<Challenge> result = new ArrayList<Challenge>();
//		result = userChallenges;
//		return result;
//	}
}
