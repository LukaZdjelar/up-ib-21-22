package com.ftn.upib.dto;

import javax.persistence.Column;

import com.ftn.upib.model.Clinic;
import com.ftn.upib.model.EUserType;
import com.ftn.upib.model.User;

public class UserDTO {

	private Long id;
	private String firstname;
	private String lastname;
	private EUserType userType;
	private String email;
	private String password;
	private String address;
	private String phoneNumber;
	private String lbo;

	private Long clinicId;
	
	public UserDTO() {
		
	}
	
	public UserDTO(User user) {
		id = user.getId();
		firstname = user.getFirstname();
		lastname = user.getLastname();
		userType = user.getUserType();
		email = user.getEmail();
		password = user.getPassword();
		address = user.getAddress();
		phoneNumber = user.getPhoneNumber();
		if (user.getUserType() == EUserType.PATIENT) {
			lbo = user.getLbo();
		}else {
			lbo = null;
		}
		
		if (user.getUserType() != EUserType.PATIENT && user.getUserType() != EUserType.ADMINISTRATOR) {
			clinicId = user.getClinic().getId();
		}else {
			clinicId = null;
		}
		
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

	public Long getClinicId() {
		return clinicId;
	}

	public void setClinicId(Long clinicId) {
		this.clinicId = clinicId;
	}
	
}
