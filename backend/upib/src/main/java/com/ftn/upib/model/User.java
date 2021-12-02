package com.ftn.upib.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "firstname", unique = false, nullable = false)
	private String firstname;
	
	@Column(name = "lastname", unique = false, nullable = false)
	private String lastname;
	
	@Column(name = "userType", unique = false, nullable = false)
	private EUserType userType;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@Column(name = "password", unique = false, nullable = false)
	private String password;
	
	@Column(name = "address", unique = false, nullable = false)
	private String address;
	
	@Column(name = "phoneNumber", unique = true, nullable = true)
	private String phoneNumber;
	
	@Column(name = "lbo", unique = true, nullable = true)
	private String lbo;
	
	@ManyToOne
	private Clinic clinic;
	
	public User() {
		
	}

	public User(Long id, String firstname, String lastname, EUserType userType, String email, String password,
			String address, String phoneNumber, String lbo, Clinic clinic) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.userType = userType;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.lbo = lbo;
		this.clinic = clinic;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public EUserType getUserType() {
		return userType;
	}

	public void setUserType(EUserType userType) {
		this.userType = userType;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLbo() {
		return lbo;
	}

	public void setLbo(String lbo) {
		this.lbo = lbo;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", userType=" + userType
				+ ", email=" + email + ", password=" + password + ", address=" + address + ", phoneNumber="
				+ phoneNumber + ", lbo=" + lbo + ", clinic=" + clinic + "]";
	}

}
