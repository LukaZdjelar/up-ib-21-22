package com.ftn.upib.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clinic")
public class Clinic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "name", unique = false, nullable = false)
	private String name;
	
	@Column(name = "address", unique = false, nullable = false)
	private String address;
	
	@Column(name = "description", unique = false, nullable = false)
	private String description;
	
	public Clinic() {
		
	}

	public Clinic(Long id, String name, String address, String description) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
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
		return "Clinic [id=" + id + ", name=" + name + ", address=" + address + ", description=" + description + "]";
	}
	
}
