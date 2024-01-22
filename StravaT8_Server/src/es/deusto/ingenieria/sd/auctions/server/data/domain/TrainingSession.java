package es.deusto.ingenieria.sd.auctions.server.data.domain;

import java.time.LocalTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TrainingSession {
	@Id	
	private String title;
	private String sport;
	private float distance;
	private Date startDate;
	private LocalTime startTime;
	private float duration;
	
	@ManyToOne
	private User user;

	
	public TrainingSession(String title, String sport, float distance, Date startDate, LocalTime timeStart, float duration, User user) {
		this.title = title;
		this.sport = sport;
		this.distance = distance;
		this.startDate = startDate;
		this.startTime = timeStart;
		this.duration = duration;
		this.user = user;
	}
	
	public TrainingSession() {
		
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSport() {
		return sport;
	}
	public void setSport(String sport) {
		this.sport = sport;
	}
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public float getDuration() {
		return duration;
	}
	public void setDuration(float duration) {
		this.duration = duration;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-YY - hh:mm");

		StringBuffer result = new StringBuffer();
		
		result.append("Session Name: ");
		result.append(this.title);
		result.append("| Date: ");
		result.append(dateFormatter.format(this.startDate));
		result.append("| Time: ");
		result.append(this.startTime);
		result.append("| Distance: ");
		result.append(distance);
		result.append("| Duration: ");
		result.append(this.duration);
		result.append("| Sport: ");
		result.append(this.sport);
		result.append("| User: ");
		result.append(this.user);
		
		return result.toString();
	}
}