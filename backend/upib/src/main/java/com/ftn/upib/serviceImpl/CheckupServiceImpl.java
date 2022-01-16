package com.ftn.upib.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.upib.model.Appointment;
import com.ftn.upib.model.CheckUp;
import com.ftn.upib.repository.CheckupRepository;
import com.ftn.upib.service.AppointmentService;
import com.ftn.upib.service.CheckupService;

@Service
public class CheckupServiceImpl implements CheckupService{

	@Autowired
	CheckupRepository checkupRespository;
	
	@Autowired
	AppointmentService appointmentService;
	
	@Override
	public CheckUp save(CheckUp checkup) {
		return checkupRespository.save(checkup);
	}

	@Override
	public List<CheckUp> findAll() {
		return checkupRespository.findAll();
	}

	@Override
	public List<CheckUp> findAllByPatient(Long id) {
		List<CheckUp> checkupList = new ArrayList<>();
		for (CheckUp checkUp : findAll()) {
			if (checkUp.getPatient().getId().equals(id)) {
				checkupList.add(checkUp);
			}
		}
		return checkupList;
	}

	@Override
	public List<Appointment> findAppointmentByPatientId(Long id) {
		List<Appointment> appointments = new ArrayList<>();
		for (CheckUp checkUp : findAll()) {
			if (checkUp.getPatient().getId().equals(id)) {
				appointments.add(checkUp.getAppointment());
			}
		}
		return appointments;
	}
}
