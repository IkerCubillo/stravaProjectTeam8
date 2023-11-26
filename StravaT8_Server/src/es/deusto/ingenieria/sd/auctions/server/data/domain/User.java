package es.deusto.ingenieria.sd.auctions.server.data.domain;

import java.util.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;

public class User {
	private String email;
	private String name;
	private String account;
	private String password;
	private Date birthDate;
	private float weight;
	private float height;
	private int mBPM;
	private int bpm;
	private ArrayList<Challenge> userChallenges = new ArrayList<Challenge>();
	private ArrayList<TrainingSession> userSessions = new ArrayList<TrainingSession>();
	
	public User(String account, String email, String name, Date birthDate, float weight, float height, int mBPM, int bpm) {
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
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	public void setName(String name) {
		this.name = name;
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

	public boolean createSession(String title, String sport, float distance, Date startDate, LocalTime timeStart, float duration) {
		return userSessions.add(new TrainingSession(title, sport, distance, startDate, timeStart, duration));
	}
	
	public List<TrainingSession> getSessions() {
		return userSessions;
	}

	//takes in DTO converts it to challenge and adds it if the user doesn't already have it
	public boolean acceptChallenge(Challenge c) {
		if(!userChallenges.contains(c)) {
			return userChallenges.add(c);
		} else {
			return false;
		}
	}
	
	public List<Challenge> dowloadActiveChallenges() {
		return userChallenges;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
