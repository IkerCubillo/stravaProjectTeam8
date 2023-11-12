package es.deusto.ingenieria.sd.auctions.server.data.domain;

import java.sql.Date;

public class User1 {
	private String email;
	private String password;
	private String account;
	private Date birthDate;
	private float weight;
	private float height;
	private int mBPM;
	private int bpm;
	
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
	
	public void createUserProfile(String account, String email, String name, Date birthDate, float weight, float height, int mBPM, int bpm) {
		
	}
}
