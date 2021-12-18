package com.ftn.upib.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "appointment")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@ManyToOne
	private User doctor;
	
	@Column(name = "dateAndTime", unique = false, nullable = false)
	private LocalDateTime dateAndTime;
	
	@Column(name = "price", unique = false, nullable = false)
	private Double price;
	
	@Column(name = "duration", unique = false, nullable = false)
	private Integer duration;
	
	@ManyToOne
	private Clinic clinic;
	
	@Column(name = "free", unique = false, nullable = false)
	private boolean free;
	
	public Appointment() {
		
	}

	public Appointment(Long id, User doctor, LocalDateTime dateAndTime, Double price, Integer duration, Clinic clinic, Boolean free) {
		super();
		this.id = id;
		this.doctor = doctor;
		this.dateAndTime = dateAndTime;
		this.price = price;
		this.duration = duration;
		this.clinic = clinic;
		this.free = free;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
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

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", doctor=" + doctor + ", dateAndTime=" + dateAndTime + ", price=" + price
				+ ", duration=" + duration + ", clinic=" + clinic + ", free=" + free + "]";
	}
	
}
