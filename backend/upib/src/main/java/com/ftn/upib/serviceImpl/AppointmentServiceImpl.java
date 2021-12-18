package com.ftn.upib.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.upib.model.Appointment;
import com.ftn.upib.repository.AppointmentRepository;
import com.ftn.upib.service.AppointmentService;

import ch.qos.logback.core.read.ListAppender;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Override
	public List<Appointment> findAll() {
		return appointmentRepository.findAll();
	}

	@Override
	public List<Appointment> findAllClinic(Long id) {
		List<Appointment> clinicAppointments = new ArrayList<>();
		List<Appointment> allAppointments = findAll();
		for (Appointment appointment : allAppointments) {
			if (appointment.getClinic().getId() == id && appointment.isFree()==true) {
				clinicAppointments.add(appointment);
			}
		}
		return clinicAppointments;
	}

}
