package es.deusto.ingenieria.sd.auctions.server.data.domain;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

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

	public void createSession(String title, String sport, float distance, Date startDate, Time timeStart, float duration) {
		userSessions.add(new TrainingSession(title, sport, distance, startDate, timeStart, duration));
	}

	public void acceptChallenge(Challenge c) {
		userChallenges.add(c);
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
