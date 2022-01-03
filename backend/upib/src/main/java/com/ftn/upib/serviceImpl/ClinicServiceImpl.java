package com.ftn.upib.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.upib.model.Appointment;
import com.ftn.upib.model.Clinic;
import com.ftn.upib.repository.AppointmentRepository;
import com.ftn.upib.repository.ClinicRepository;
import com.ftn.upib.service.AppointmentService;
import com.ftn.upib.service.ClinicService;

@Service
public class ClinicServiceImpl implements ClinicService{

	@Autowired
	ClinicRepository clinicRepository;
	
	@Autowired
	AppointmentService appointmentService;
	
	@Override
	public List<Clinic> findAll() {
		return clinicRepository.findAll();
	}

	@Override
	public Clinic findClinicById(Long id) {
		return clinicRepository.findClinicById(id);
	}

	@Override
	public List<Clinic> findClinicsByAppointmentDate(LocalDate date) {
		List<Clinic> clinicsToShow = new ArrayList<>();
		if (date == null) {
			clinicsToShow = findAll();
		}else {
			List<Clinic> clinics = findAll();
			for (Clinic clinic : clinics) {
				for (Appointment appointment: appointmentService.findAllClinic(clinic.getId())) {
					if (appointment.getDateAndTime().toLocalDate().equals(date) && appointment.isFree()) {
						clinicsToShow.add(clinic);
						break;
					}
				}
			}
		}
		
		return clinicsToShow;
	}
}
