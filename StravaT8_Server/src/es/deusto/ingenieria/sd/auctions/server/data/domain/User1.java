package es.deusto.ingenieria.sd.auctions.server.data.domain;

import java.util.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;

public class User1 {
	private String email;
	private String password;
	private String account;
	private Date birthDate;
	private float weight;
	private float height;
	private int mBPM;
	private int bpm;
	private ArrayList<Challenge> userChallenges = new ArrayList<Challenge>();
	private ArrayList<TrainingSession> userSessions = new ArrayList<TrainingSession>();
	
	public User1(String account, String password, String email, String name, Date birthDate, float weight, float height, int mBPM, int bpm) {
		this.email = email;
		this.password = password;
		this.account = account;
		this.birthDate = birthDate;
		this.weight = weight;
		this.height = height;
		this.mBPM = mBPM;
		this.bpm = bpm;
	}
	
	public User1() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public void createSession(String title, String sport, float distance, Date startDate, LocalTime timeStart, float duration) {
		userSessions.add(new TrainingSession(title, sport, distance, startDate, timeStart, duration));
	}

	//takes in DTO converts it to challenge and adds it if the user doesn't already have it
	public void acceptChallenge(ChallengeDTO c) {
		Challenge temp = new Challenge(c);
		if(!userChallenges.contains(temp)) {
			userChallenges.add(temp);
		}
	}
	
	public List<Challenge> dowloadActiveChallenges() {
		return userChallenges;
	}

	public boolean setupDistanceChallenge(String name, Date start, Date end, float metric, String sportType) {
		
		return true;
	}

	public boolean setupActivityTimeChallenge(String name, Date start, Date end, float metric, String sportType) {
		return true;
	}

}
