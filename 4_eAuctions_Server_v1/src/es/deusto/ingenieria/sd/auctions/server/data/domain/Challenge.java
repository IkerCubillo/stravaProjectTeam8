package es.deusto.ingenieria.sd.auctions.server.data.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-YY - hh:mm");

		StringBuffer result = new StringBuffer();
		
		result.append("Challenge Name: ");
		result.append(this.name);
		result.append("| Start: ");
		result.append(dateFormatter.format(this.start));
		result.append("| End: ");
		result.append(dateFormatter.format(this.end));
		result.append("| Goal: ");
		result.append(dateFormatter.format(this.metric));
		result.append("| Sport: ");
		result.append(dateFormatter.format(this.sportType));
		
		return result.toString();
	}
}
