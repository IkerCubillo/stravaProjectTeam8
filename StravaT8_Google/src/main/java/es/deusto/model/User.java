package es.deusto.model;

import jakarta.persistence.*;

// "User" is a reserved word in many DBs. We can programmatically provide a name for the table to avoid problems.
@Table(name="userTable")
@Entity
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    
    @Column(unique = true)
    private String email;
      
    public User () {}
    
    public User (String email, String password) {
    	this.email=email;
    	this.password = password;
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
    
	public Long getId() {
		return id;
	}
}
