package es.deusto.ingenieria.sd.auctions.server.data.domain;

import java.io.Serializable;
import java.util.Date;

import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
//This class implements DTO pattern
@Entity
public class Challenge implements Serializable {
	//This attribute is needed to implement the "Serializable" interface.
	@Id
	private String name;
	private Date start;
	private Date end;
	private float metric;
	private String sportType;
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private User user = null;
	
	public Challenge(String name, Date start, Date end, float metric, String sportType, User user) {
		this.name = name;
		this.start = start;
		this.end = end;
		this.metric = metric;
		this.sportType = sportType;
		this.user = user;
	}
	
	public Challenge(ChallengeDTO c, User user) {
		this.name = c.getName();
		this.start = (Date) c.getStart();
		this.end = (Date) c.getEnd();
		this.metric = c.getMetric();
		this.sportType = c.getSportType();
		this.user = user;
	}
	
	public Challenge() {
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public float getMetric() {
		return metric;
	}

	public void setMetric(float metric) {
		this.metric = metric;
	}

	public String getSportType() {
		return sportType;
	}

	public void setSportType(String sportType) {
		this.sportType = sportType;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {

		StringBuffer result = new StringBuffer();
		
		result.append("Challenge Name: ");
		result.append(this.name);
		result.append("| Start: ");
		result.append(this.start);
		result.append("| End: ");
		result.append(this.end);
		result.append("| Goal: ");
		result.append(this.metric);
		result.append("| Sport: ");
		result.append(this.sportType);
		result.append("| User: ");
		result.append(this.user);
		
		return result.toString();
	}
	
	public String toEmail() {
		String email = "Hi, Strava user!\nYour new Challenge has been submited. This is the description of your challenge:\nChallenge Name: " + 
				this.name + "\nStart Date: " + this.start + "\nEnd Date: " + this.end + "\nGoal: " + this.metric + "\nSport type: " + this.sportType + 
				"\n\nWe wish you have an excellent experience with our app!!\nStrava Team";
		
		return email;
	}
	
	public boolean equals(Challenge c) {
		if (this.getName().equals(c.getName()) && this.getStart().equals(c.getStart()) && this.getEnd().equals(c.getEnd()) && this.getMetric() == c.getMetric() && this.getSportType().equals(c.getSportType()) && this.getUser().equals(c.getUser())) {
			return true;
		}
		
		return false;
	}
}