package es.deusto.ingenieria.sd.auctions.server.data.domain;

import java.util.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
	private String password;
	private Date birthDate;
	private float weight;
	private float height;
	private int mBPM;
	private int bpm;
	

	

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	private ArrayList<TrainingSession> userSessions = new ArrayList<TrainingSession>();
	private static ArrayList<Challenge> userChallenges = new ArrayList<Challenge>();
	private ArrayList<Float> challengesPercentages = new ArrayList<Float>();
	
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
		TrainingSession tr = new TrainingSession(title, sport, distance, startDate, timeStart, duration, this.getEmail());
		TrainingSessionDAO.getInstance().save(tr);
		return userSessions.add(tr);
	}
	
	public List<TrainingSession> getSessions() {
		return userSessions;
	}

	//takes in challenge and adds it if the user doesn't already have it
	public boolean acceptChallenge(Challenge c) {
		for (Challenge temp : userChallenges) {
			if (c.equals(temp)) {
				return false;
			}
		}
		userChallenges.add(c);
		return true;
	}
	
	public static ArrayList<Challenge> getUserChallenges() {
		ArrayList<Challenge> result = new ArrayList<Challenge>();
		result = userChallenges;
		return result;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
