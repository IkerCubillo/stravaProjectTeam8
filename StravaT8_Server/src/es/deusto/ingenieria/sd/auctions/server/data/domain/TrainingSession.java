package es.deusto.ingenieria.sd.auctions.server.data.domain;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class TrainingSession implements Serializable {
	private static final long serialVersionUID = 1L;
	private String title;
	private String sport;
	private float distance;
	private Date startDate;
	private Time startTime;
	private float duration;
	
	public TrainingSession(String title, String sport, float distance, Date startDate, Time timeStart, float duration) {
		this.title = title;
		this.sport = sport;
		this.distance = distance;
		this.startDate = startDate;
		this.startTime = timeStart;
		this.duration = duration;
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
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public float getDuration() {
		return duration;
	}
	public void setDuration(float duration) {
		this.duration = duration;
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
		
		return result.toString();
	}
}

