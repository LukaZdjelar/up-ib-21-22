package com.ftn.upib.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.ftn.upib.model.Clinic;

public interface ClinicService {

	List<Clinic> findAll();
	Clinic findClinicById(Long id);
	List<Clinic> findClinicsByAppointmentDate(LocalDate date);
	List<Clinic> searchByAny(String term);
}
