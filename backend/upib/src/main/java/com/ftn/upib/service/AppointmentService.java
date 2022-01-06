package com.ftn.upib.service;

import java.util.List;

import com.ftn.upib.model.Appointment;

public interface AppointmentService {

	List<Appointment> findAll();
	List<Appointment> findAllClinic(Long id);
	Appointment findAppointmentById(Long id);
	Appointment create(Appointment appointment);
	Appointment schedule(Appointment appointment);
}
