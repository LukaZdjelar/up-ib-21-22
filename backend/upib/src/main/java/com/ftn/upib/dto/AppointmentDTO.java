package com.ftn.upib.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

import com.ftn.upib.model.Appointment;
import com.ftn.upib.model.Clinic;
import com.ftn.upib.model.User;

public class AppointmentDTO {

	private Long id;
	private Long doctorId;
	private String doctorFirstname;
	private String doctorLastname;
	private LocalDateTime dateAndTime;
	private LocalDateTime endDateAndTime;
	private Double price;
	private Integer duration;
	private Long clinicId;
	private String clinicName;
	private boolean free;
	
	public AppointmentDTO(Appointment appointment) {
		id = appointment.getId();
		doctorId = appointment.getDoctor().getId();
		doctorFirstname = appointment.getDoctor().getFirstname();
		doctorLastname = appointment.getDoctor().getLastname();
		dateAndTime = appointment.getDateAndTime();
		price = appointment.getPrice();
		duration = appointment.getDuration();
		endDateAndTime = dateAndTime.plusMinutes(duration);
		clinicId = appointment.getClinic().getId();
		clinicName = appointment.getClinic().getName();
		free = appointment.isFree();
	}
	
	public AppointmentDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorFirstname() {
		return doctorFirstname;
	}

	public void setDoctorFirstname(String doctorFirstname) {
		this.doctorFirstname = doctorFirstname;
	}

	public String getDoctorLastname() {
		return doctorLastname;
	}

	public void setDoctorLastname(String doctorLastname) {
		this.doctorLastname = doctorLastname;
	}

	public LocalDateTime getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(LocalDateTime dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public LocalDateTime getEndDateAndTime() {
		return endDateAndTime;
	}

	public void setEndDateAndTime(LocalDateTime endDateAndTime) {
		this.endDateAndTime = endDateAndTime;
	}

	public Long getClinicId() {
		return clinicId;
	}

	public void setClinicId(Long clinicId) {
		this.clinicId = clinicId;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}
}
