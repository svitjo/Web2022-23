package entities;

import java.util.Date;

import enums.UserRole;

public class User {
	private int id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String gender;
	private Date birthday;
	private UserRole role;
	private boolean hasRentACarObject;
	public boolean getHasRentACarObject() {
		return hasRentACarObject;
	}

	public void setHasRentACarObject(boolean hasRentACarObject) {
		this.hasRentACarObject = hasRentACarObject;
	}
	
	public User() {}
	
	public User(String username, String password, String firstname, String lastname, String gender, Date birthday, UserRole role, boolean hasRentACarObject) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.birthday = birthday;
		this.role = role;
		this.hasRentACarObject=hasRentACarObject;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}