package es.deusto.ingenieria.sd.auctions.server.data.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.sms.server.gateway.MailSender;


//This class implements DTO pattern
public class Challenge implements Serializable {
	//This attribute is needed to implement the "Serializable" interface.
	private static final long serialVersionUID = 1L;
	private String name;
	private Date start;
	private Date end;
	private float metric;
	private String sportType;
	
	public Challenge(String name, Date start, Date end, float metric, String sportType) {
		this.name = name;
		this.start = start;
		this.end = end;
		this.metric = metric;
		this.sportType = sportType;
		new MailSender("kerman.bruna@opendeusto.es").sendMessage("cubillo es un cuajado");
	}
	
	public Challenge(ChallengeDTO c) {
		this.name = c.getName();
		this.start = c.getStart();
		this.end = c.getEnd();
		this.metric = c.getMetric();
		this.sportType = c.getSportType();
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
		
		return result.toString();
	}
	
	public boolean equals(Challenge c) {
		if (this.getName().equals(c.getName()) && this.getStart().equals(c.getStart()) && this.getEnd().equals(c.getEnd()) && this.getMetric() == c.getMetric() && this.getSportType().equals(c.getSportType())) {
			return true;
		}
		
		return false;
	}
}
