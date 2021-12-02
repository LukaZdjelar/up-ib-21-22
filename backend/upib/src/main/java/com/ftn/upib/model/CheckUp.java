package com.ftn.upib.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "checkup")
public class CheckUp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@ManyToOne
	private Appointment appointment;
	
	@ManyToOne
	private User patient;
	
	@Column(name = "description", unique = false, nullable = false)
	private String description;
	
	public CheckUp() {
		
	}

	public CheckUp(Long id, Appointment appointment, User patient, String description) {
		super();
		this.id = id;
		this.appointment = appointment;
		this.patient = patient;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public User getPatient() {
		return patient;
	}

	public void setPatient(User patient) {
		this.patient = patient;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "CheckUp [id=" + id + ", appointment=" + appointment + ", patient=" + patient + ", description="
				+ description + "]";
	}
	
}
