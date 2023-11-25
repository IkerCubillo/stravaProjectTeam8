package es.deusto.ingenieria.sd.auctions.server.data.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

//This class implements DTO pattern
public class ChallengeDTO implements Serializable {
	//This attribute is needed to implement the "Serializable" interface.
	private static final long serialVersionUID = 1L;
	private String name;
	private Date start;
	private Date end;
	private float metric;
	private String sportType;
	
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
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-YY - hh:mm");

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
}
