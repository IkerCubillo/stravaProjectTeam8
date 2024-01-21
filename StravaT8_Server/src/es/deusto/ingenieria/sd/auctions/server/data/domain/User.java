package es.deusto.ingenieria.sd.auctions.server.data.domain;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



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
		if (trainingSession != null) {
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
		if (challenge != null) {
			this.userActiveChallenges.add(challenge);
		}
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", name=" + name + ", account=" + account + ", birthDate=" + birthDate
				+ ", weight=" + weight + ", height=" + height + ", mBPM=" + mBPM + ", bpm=" + bpm + ", userSessions="
				+ userSessions;
	}
	
	
}
