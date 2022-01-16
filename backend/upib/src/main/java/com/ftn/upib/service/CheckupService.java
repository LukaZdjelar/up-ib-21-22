package com.ftn.upib.service;

import java.util.List;

import com.ftn.upib.model.Appointment;
import com.ftn.upib.model.CheckUp;

public interface CheckupService {
	CheckUp save(CheckUp checkup);
	List<CheckUp> findAll();
	List<CheckUp> findAllByPatient(Long id);
	List<Appointment> findAppointmentByPatientId(Long id);
}
