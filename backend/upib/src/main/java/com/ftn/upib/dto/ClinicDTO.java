package com.ftn.upib.dto;

import javax.persistence.Column;

import com.ftn.upib.model.Clinic;

public class ClinicDTO {

	private Long id;
	private String name;
	private String address;
	private String description;
	
	public ClinicDTO() {
		
	}
	
	public ClinicDTO(Clinic clinic) {
		id = clinic.getId();
		name = clinic.getName();
		address = clinic.getAddress();
		description = clinic.getDescription();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ClinicDTO [id=" + id + ", name=" + name + ", address=" + address + ", description=" + description + "]";
	}
}
