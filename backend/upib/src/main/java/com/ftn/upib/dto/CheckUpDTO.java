package com.ftn.upib.dto;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.ftn.upib.model.Appointment;
import com.ftn.upib.model.CheckUp;
import com.ftn.upib.model.User;

public class CheckUpDTO {

	private Long id;	
	private Long appointmentId;
	private Long patientId;
	private String description;
	
	public CheckUpDTO() {
		
	}

	public CheckUpDTO(CheckUp checkup) {
		if(checkup.getId() != null) {
			id = checkup.getId();
		}
		appointmentId = checkup.getAppointment().getId();
		patientId = checkup.getPatient().getId();
		description = checkup.getDescription();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "CheckUpDTO [id=" + id + ", appointmentId=" + appointmentId + ", patientId=" + patientId
				+ ", description=" + description + "]";
	}
	
}
