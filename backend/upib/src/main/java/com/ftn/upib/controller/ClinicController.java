package com.ftn.upib.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ftn.upib.dto.AppointmentDTO;
import com.ftn.upib.dto.ClinicDTO;
import com.ftn.upib.dto.FilterDTO;
import com.ftn.upib.model.Appointment;
import com.ftn.upib.model.Clinic;
import com.ftn.upib.service.AppointmentService;
import com.ftn.upib.service.ClinicService;

@Controller
@RequestMapping("/clinic")
@CrossOrigin(origins = "http://localhost:3000")
public class ClinicController {

	@Autowired
	ClinicService clinicService;

	@Autowired
	AppointmentService appointmentService;

	@GetMapping
	private ResponseEntity<List<ClinicDTO>> findAll() {
		List<ClinicDTO> clinicDTOList = new ArrayList<>();
		for (Clinic clinic : clinicService.findAll()) {
			clinicDTOList.add(new ClinicDTO(clinic));
		}
		return new ResponseEntity<>(clinicDTOList, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	private ResponseEntity<ClinicDTO> findOne(@PathVariable("id") Long id) {
		Clinic clinic = clinicService.findClinicById(id);
		return new ResponseEntity<>(new ClinicDTO(clinic), HttpStatus.OK);
	}

	@GetMapping(value = "/{clinicId}/{doctorId}")
	private ResponseEntity<List<AppointmentDTO>> findClinicAppointments(@PathVariable("clinicId") Long clinicId,
			@PathVariable("doctorId") Long doctorId) {
		List<Appointment> clinicAppointments = appointmentService.findAllClinic(clinicId);
		List<AppointmentDTO> appointmentsDTO = new ArrayList<>();
		for (Appointment appointment : clinicAppointments) {
			if (appointment.getDoctor().getId().equals(doctorId)) {
				appointmentsDTO.add(new AppointmentDTO(appointment));
			}
		}
		return new ResponseEntity<>(appointmentsDTO, HttpStatus.OK);
	}

	@PostMapping("/search")
	private ResponseEntity<List<ClinicDTO>> findAllByDate(@RequestBody FilterDTO filter) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
		LocalDate date = LocalDate.parse(filter.getDate(), formatter);
		List<ClinicDTO> clinicDTOList = new ArrayList<>();

		if (!filter.getTerm().equals("")) {
			for (Clinic clinic : clinicService.findClinicsByAppointmentDate(date)) {
				if (clinicService.searchByAny(filter.getTerm()).contains(clinic)) {
					clinicDTOList.add(new ClinicDTO(clinic));
				}
			}
		} else {
			for (Clinic clinic : clinicService.findClinicsByAppointmentDate(date)) {
				clinicDTOList.add(new ClinicDTO(clinic));
			}
		}

		return new ResponseEntity<>(clinicDTOList, HttpStatus.OK);
	}
}
